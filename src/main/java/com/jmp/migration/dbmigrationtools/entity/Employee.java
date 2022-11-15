package com.jmp.migration.dbmigrationtools.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_id_seq",
                        sequenceName = "employee_id_seq",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "employee_id_seq")
    private Long id;

    private String name;

    private Integer age;

    @ManyToOne
    private Company company;
}
