package io.kenzo.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "departments")

public class Department extends BaseEntity{
    private String departmentName;
    private String departmentCode;
    private String description;
    private String officeLocation;
    private String email;
}
