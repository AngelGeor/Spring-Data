package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCountryDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final Validator validator;



    public CountryServiceImpl(ModelMapper modelMapper, CountryRepository countryRepository, Gson gson, Validator validator) {
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

  @Override
  public String readCountriesFromFile() throws IOException {
       return Files.readString(Path.of(COUNTRIES_FILE_PATH));
  }
//
   @Override
   public String importCountries() throws IOException {
       String json = this.readCountriesFromFile();

       ImportCountryDTO[] ImportCountryDTOs = this.gson.fromJson(json, ImportCountryDTO[].class);

       List<String> printResults = new ArrayList<>();

       for (ImportCountryDTO ImportCountryDTO : ImportCountryDTOs) {
           Set<ConstraintViolation<ImportCountryDTO>> validationErrors =
                   this.validator.validate(ImportCountryDTO);

           if (validationErrors.isEmpty()) {
               Optional<Country> optTown =
                       this.countryRepository.findByCountryName(ImportCountryDTO.getCountryName());

               if (optTown.isEmpty()) {
                   Country country = this.modelMapper.map(ImportCountryDTO, Country.class);

                   this.countryRepository.save(country);

                   String msg = String.format("Successfully imported country %s - %s",
                           country.getCountryName(), country.getCurrency());

                   printResults.add(msg);
               } else {
                   printResults.add("Invalid country");
               }
           } else {
               printResults.add("Invalid country");
           }
       }

       return String.join("\n", printResults);
   }
}