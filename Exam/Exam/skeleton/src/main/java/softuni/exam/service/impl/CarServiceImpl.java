package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCars.ImportCarsDTO;
import softuni.exam.models.dto.ImportCars.ImportCarsRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_PATH_FILE = "src/main/resources/files/xml/cars.xml";
    private final CarRepository carRepository;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, Validator validator, ModelMapper modelMapper) throws JAXBException {
        this.carRepository = carRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(ImportCarsRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_PATH_FILE));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        ImportCarsRootDTO carsDTos = (ImportCarsRootDTO) this.unmarshaller.unmarshal(
                new FileReader(CARS_PATH_FILE));


        return carsDTos
                .getCars()
                .stream()
                .map(this::importCar)
                .collect(Collectors.joining("\n"));
    }
    private String importCar(ImportCarsDTO dto) {
        Set<ConstraintViolation<ImportCarsDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid car";
        }

        Optional<Car> optCar =
                this.carRepository
                        .findByPlateNumber(dto.getPlateNumber());

        if (optCar.isPresent()) {
            return "Invalid car";
        }

        Car car = this.modelMapper.map(dto, Car.class);

        this.carRepository.save(car);

        return String.format("Successfully imported car %s - %s",
                car.getCarMake(),car.getCarModel());
    }
}
