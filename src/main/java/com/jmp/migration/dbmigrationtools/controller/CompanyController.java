package com.jmp.migration.dbmigrationtools.controller;

import com.jmp.migration.dbmigrationtools.dto.CompanyResDto;
import com.jmp.migration.dbmigrationtools.dto.CreateCompanyDto;
import com.jmp.migration.dbmigrationtools.entity.Company;
import com.jmp.migration.dbmigrationtools.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public ResponseEntity<CompanyResDto> createCompany(@RequestBody CreateCompanyDto createCompanyDto) {
        CompanyResDto company = companyService.createCompany(createCompanyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResDto> getCompany(@PathVariable Long id) {
        CompanyResDto company = companyService.getCompany(id);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResDto>> getAllCompany() {
        List<CompanyResDto> companies = companyService.getAllCompany();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/add-employee/{id}")
    public ResponseEntity<CompanyResDto> addEmployeeToCompany(
            @PathVariable Long id, @RequestParam("employees") List<Long> employeeIds) {
        CompanyResDto company = companyService.addEmployeeToCompany(id, employeeIds);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

}
