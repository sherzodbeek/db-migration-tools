package com.jmp.migration.dbmigrationtools.dto;

import com.jmp.migration.dbmigrationtools.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResDto {

    Long id;

    private String name;

    List<CompanyEmployeeDto> employees;
}
