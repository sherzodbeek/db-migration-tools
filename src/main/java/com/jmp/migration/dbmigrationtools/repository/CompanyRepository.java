package com.jmp.migration.dbmigrationtools.repository;

import com.jmp.migration.dbmigrationtools.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
