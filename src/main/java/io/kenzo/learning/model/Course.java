package io.kenzo.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "courses")

@ToString
@NoArgsConstructor
public class Course extends BaseEntity{


        private String courseName;
        private String courseCode;
        private String description;
        private int duration;
        private int credits;
        private String instructor;
        private String department;
        private double tuitionFee;


    }


