package com.jmp.migration.dbmigrationtools.controller;

import com.jmp.migration.dbmigrationtools.dto.CreateEmployeeDto;
import com.jmp.migration.dbmigrationtools.dto.EmployeeResDto;
import com.jmp.migration.dbmigrationtools.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<EmployeeResDto> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        EmployeeResDto employee = employeeService.createEmployee(createEmployeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResDto> getEmployee(@PathVariable Long id) {
        EmployeeResDto employee = employeeService.getEmployee(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResDto>> getAllEmployee() {
        List<EmployeeResDto> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
}
