package com.jmp.migration.dbmigrationtools.repository;

import com.jmp.migration.dbmigrationtools.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
}
