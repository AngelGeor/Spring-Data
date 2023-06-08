package com.example.football.service.impl;

import com.example.football.models.dto.ImportTown.ImportTownDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {
    private static final String TOWN_PATH_FILE = "src/main/resources/files/json/towns.json";
    private final Gson gson;
    private final Validator validator;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(Gson gson, TownRepository townRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
      return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
    return Files.readString(Path.of(TOWN_PATH_FILE));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        ImportTownDto[] importTownDTOs = this.gson.fromJson(json, ImportTownDto[].class);

        List<String> result = new ArrayList<>();

        for (ImportTownDto importTownDTO : importTownDTOs) {
            Set<ConstraintViolation<ImportTownDto>> validationErrors =
                    this.validator.validate(importTownDTO);

            if (validationErrors.isEmpty()) {
                Optional<Town> optTown =
                        this.townRepository.findByName(importTownDTO.getName());

                if (optTown.isEmpty()) {
                    Town town = this.modelMapper.map(importTownDTO, Town.class);

                    this.townRepository.save(town);

                    String msg = String.format("Successfully imported Town %s - %d",
                            town.getName(), town.getPopulation());

                    result.add(msg);
                } else {
                    result.add("Invalid Town");
                }
            } else {
                result.add("Invalid Town");
            }
        }

        return String.join("\n", result);
    }
}
