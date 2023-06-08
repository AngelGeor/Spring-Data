package com.example.modelmapperlab.services;

import com.example.modelmapperlab.entities.Employee;
import com.example.modelmapperlab.entities.dto.CreateEmployeeDTO;
import com.example.modelmapperlab.entities.dto.EmployeeDTO;
import com.example.modelmapperlab.entities.dto.EmployeeNameAndSalaryDTO;
import com.example.modelmapperlab.entities.dto.EmployeeNamesDTO;

import java.util.List;


public interface EmployeeService {
     Employee create(CreateEmployeeDTO employeeDTO);

     List<EmployeeDTO> findAll();

     EmployeeNamesDTO findNamesById(long id);

     EmployeeNameAndSalaryDTO findNameAndSalaryById(long id);
}