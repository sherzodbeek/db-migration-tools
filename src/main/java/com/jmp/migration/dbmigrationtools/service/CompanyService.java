package com.jmp.migration.dbmigrationtools.service;

import com.jmp.migration.dbmigrationtools.dto.CompanyEmployeeDto;
import com.jmp.migration.dbmigrationtools.dto.CompanyResDto;
import com.jmp.migration.dbmigrationtools.dto.CreateCompanyDto;
import com.jmp.migration.dbmigrationtools.entity.Company;
import com.jmp.migration.dbmigrationtools.entity.Employee;
import com.jmp.migration.dbmigrationtools.repository.CompanyRepository;
import com.jmp.migration.dbmigrationtools.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;


    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }


    public CompanyResDto createCompany(CreateCompanyDto createCompanyDto) {
        Company newCompany = Company.builder().name(createCompanyDto.getName())
                .build();
        return mapCompanyToCompanyResDto(companyRepository.save(newCompany));
    }

    public CompanyResDto getCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        return mapCompanyToCompanyResDto(company);
    }

    public CompanyResDto addEmployeeToCompany(Long companyId, List<Long> employeeIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            return null;
        }
        employees.forEach(employee -> employee.setCompany(company));
        company.setEmployee(employees);
        Company savedCompany = companyRepository.save(company);
        return mapCompanyToCompanyResDto(savedCompany);
    }

    public List<CompanyResDto> getAllCompany() {
        return companyRepository.findAll().stream().map(this::mapCompanyToCompanyResDto).collect(Collectors.toList());
    }

    private CompanyResDto mapCompanyToCompanyResDto(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResDto companyResDto = new CompanyResDto();
        companyResDto.setId(company.getId());
        companyResDto.setName(company.getName());
        if (company.getEmployee() != null) {
            companyResDto.setEmployees(company.getEmployee().stream()
                    .map(employee -> new CompanyEmployeeDto(employee.getId(), employee.getName()))
                    .collect(Collectors.toList()));
        } else {
            companyResDto.setEmployees(new ArrayList<>());
        }
        return companyResDto;
    }
}
