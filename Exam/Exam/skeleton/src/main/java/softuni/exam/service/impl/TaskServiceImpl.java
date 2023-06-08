package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTasks.ImportTasksDTO;
import softuni.exam.models.dto.ImportTasks.ImportTasksRootDTO;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;

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
public class TaskServiceImpl implements TaskService {
    private static final String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private final TaskRepository taskRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final MechanicRepository mechanicRepository;

    private final CarRepository carRepository;

    private final PartRepository partRepository;

    private final CarType carType = CarType.coupe;

    public TaskServiceImpl(TaskRepository taskRepository, Validator validator, ModelMapper modelMapper, MechanicRepository mechanicRepository, CarRepository carRepository, PartRepository partRepository) throws JAXBException {
        this.taskRepository = taskRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.mechanicRepository = mechanicRepository;
        this.carRepository = carRepository;
        this.partRepository = partRepository;

        JAXBContext context = JAXBContext.newInstance(ImportTasksRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
      ImportTasksRootDTO taskDTOs = (ImportTasksRootDTO) this.unmarshaller.unmarshal(new FileReader(TASKS_FILE_PATH));

      return taskDTOs
              .getTasks()
              .stream()
              .map(this::importTask)
              .collect(Collectors.joining("\n"));
    }

    private String importTask(ImportTasksDTO dto) {
        Set<ConstraintViolation<ImportTasksDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid task";
        }

        Optional<Mechanic> optMechanic = this.mechanicRepository.findByFirstName(dto.getMechanic().getFirstName());

        if(optMechanic.isEmpty()) {
            return "Invalid task";
        }

        Optional<Car> car = this.carRepository.findById(dto.getCar().getId());
        Optional<Mechanic> mechanic = this.mechanicRepository.findByFirstName(dto.getMechanic().getFirstName());
        Optional<Part> part = this.partRepository.findById(dto.getPart().getId());

        Task task = this.modelMapper.map(dto, Task.class);

        task.setCar(car.get());
        task.setMechanic(mechanic.get());
        task.setPart(part.get());

        this.taskRepository.save(task);
        return String.format("Successfully imported task %.2f",task.getPrice());
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        taskRepository.findAllByCar_CarTypeOrderByPriceDesc(carType).forEach(task -> {

            sb.append(String.format("Car %s %s with %dkm%n" +
                    "-Mechanic: %s %s - task №%d%n" +
                    "--Engine: %.1f%n" +
                    "---Price: %.2f$%n",
                    task.getCar().getCarMake(), task.getCar().getCarModel(), task.getCar().getKilometers(),
                    task.getMechanic().getFirstName(), task.getMechanic().getLastName(),task.getId(),
                    task.getCar().getEngine(),
                    task.getPrice()
                    ));


        });


        return sb.toString();
    }
}
