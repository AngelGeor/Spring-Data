package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportMechanic.ImportMechanicsDTO;
import softuni.exam.models.dto.ImportPart.ImportPartsDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MechanicServiceImpl implements MechanicService {
    private static final String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";
    private final MechanicRepository mechanicRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, Gson gson, ModelMapper modelMapper, Validator validator) {
        this.mechanicRepository = mechanicRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        String json = this.readMechanicsFromFile();
        ImportMechanicsDTO[] mechanicsDTOS = this.gson.fromJson(json, ImportMechanicsDTO[].class);

        return Arrays.stream(mechanicsDTOS)
                .map(this::importMechanics)
                .collect(Collectors.joining("\n"));
    }

    private String importMechanics(ImportMechanicsDTO dto){
        Set<ConstraintViolation<ImportMechanicsDTO>> errors = this.validator.validate(dto);
        if(!errors.isEmpty()){
            return "Invalid mechanic";
        }

        Optional<Mechanic> optMechanic = this.mechanicRepository.findByEmail(dto.getEmail());
        if(optMechanic.isPresent()){
            return "Invalid mechanic";
        }

        Mechanic mechanic = this.modelMapper.map(dto, Mechanic.class);
        this.mechanicRepository.save(mechanic);
        return String.format("Successfully imported mechanic %s %s",
                mechanic.getFirstName(),mechanic.getLastName());
    }
}
