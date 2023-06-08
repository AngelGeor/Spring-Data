package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPart.ImportPartsDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;

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
public class PartServiceImpl implements PartService {
    private static final String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartRepository partRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, Gson gson, Validator validator, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {
       String json = this.readPartsFileContent();
        ImportPartsDTO[] partsDTOS = this.gson.fromJson(json, ImportPartsDTO[].class);

        return Arrays.stream(partsDTOS)
                .map(this::importParts)
                .collect(Collectors.joining("\n"));
    }

    private String importParts(ImportPartsDTO dto){
        Set<ConstraintViolation<ImportPartsDTO>> errors = this.validator.validate(dto);
        if(!errors.isEmpty()){
            return "Invalid part";
        }

        Optional<Part> optPart = this.partRepository.findByPartName(dto.getPartName());
        if(optPart.isPresent()){
            return "Invalid part";
        }

        Part part = this.modelMapper.map(dto, Part.class);
        this.partRepository.save(part);
        return String.format("Successfully imported part %s - %.2f",
                part.getPartName(), part.getPrice());
    }
}
