package com.example.modelmapperlab;
import com.example.modelmapperlab.entities.Address;
import com.example.modelmapperlab.entities.Employee;
import com.example.modelmapperlab.entities.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import java.math.BigDecimal;


//@Component
public class CommandLineRunnerMain implements org.springframework.boot.CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();

//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//            }
//        };
//        mapper.addMappings(propertyMap);
//        mapper.validate();

        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping -> mapping.map(
                source -> source.getAddress().getCity(),
                EmployeeDTO::setAddressCity)
        );
//        typeMap.validate();

        Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("First", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = typeMap.map(employee);

        System.out.println(employeeDTO);
    }
}