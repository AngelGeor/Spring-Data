package com.example.modelmapperlab.repositories;

import com.example.modelmapperlab.entities.Employee;
import com.example.modelmapperlab.entities.dto.EmployeeNameAndSalaryDTO;
import com.example.modelmapperlab.entities.dto.EmployeeNamesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.example.modelmapperlab.entities.dto.EmployeeNamesDTO(e.firstName, e.lastName)" +
            " FROM Employee AS e WHERE e.id = :id")
    EmployeeNamesDTO findNamesById(long id);

    EmployeeNameAndSalaryDTO findFirstNameAndSalaryById(long id);
}
