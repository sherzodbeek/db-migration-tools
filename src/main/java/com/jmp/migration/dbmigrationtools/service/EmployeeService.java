package com.jmp.migration.dbmigrationtools.service;

import com.jmp.migration.dbmigrationtools.dto.CreateEmployeeDto;
import com.jmp.migration.dbmigrationtools.dto.EmployeeCompanyDto;
import com.jmp.migration.dbmigrationtools.dto.EmployeeResDto;
import com.jmp.migration.dbmigrationtools.entity.Employee;
import com.jmp.migration.dbmigrationtools.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResDto createEmployee(CreateEmployeeDto createEmployeeDto) {
        Employee newEmployee = Employee.builder().name(createEmployeeDto.getName())
               .build();
        return mapEmployeeToEmployeeResDto(employeeRepository.save(newEmployee));
    }

    public EmployeeResDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return mapEmployeeToEmployeeResDto(employee);
    }

    public List<EmployeeResDto> getAllEmployee() {
        return employeeRepository.findAll().stream().map(this::mapEmployeeToEmployeeResDto).collect(Collectors.toList());
    }

    private EmployeeResDto mapEmployeeToEmployeeResDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeResDto employeeResDto = new EmployeeResDto();
        employeeResDto.setId(employee.getId());
        employeeResDto.setName(employee.getName());
        if (employee.getCompany() != null) {
            employeeResDto.setCompany(new EmployeeCompanyDto(employee.getCompany().getId(), employee.getCompany().getName()));
        }
        return employeeResDto;
    }
}
