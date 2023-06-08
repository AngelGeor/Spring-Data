package com.example.football.service.impl;

import com.example.football.models.dto.ImportStat.ImportStatDTO;
import com.example.football.models.dto.ImportStat.ImportStatRootDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    private static final String STAT_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Unmarshaller unmarshaller;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper) throws JAXBException {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(ImportStatRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
     return  Files.readString(Path.of(STAT_FILE_PATH));
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {
        ImportStatRootDTO statDTOs = (ImportStatRootDTO) this.unmarshaller.unmarshal(
                new FileReader(STAT_FILE_PATH));


        return statDTOs
                .getStats()
                .stream()
                .map(this::importStat)
                .collect(Collectors.joining("\n"));
    }

    private String importStat(ImportStatDTO dto) {
        Set<ConstraintViolation<ImportStatDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid Stat";
        }

        Optional<Stat> optStat =
                this.statRepository
                        .findByShootingAndPassingAndEndurance(dto.getShooting(),
                                dto.getPassing(), dto.getEndurance());

        if (optStat.isPresent()) {
            return "Invalid Stat";
        }

        Stat stat = this.modelMapper.map(dto, Stat.class);

        this.statRepository.save(stat);

        return String.format("Successfully imported Stat %.2f - %.2f - %.2f%n",
                stat.getShooting(),stat.getPassing(), stat.getEndurance());
    }
}
