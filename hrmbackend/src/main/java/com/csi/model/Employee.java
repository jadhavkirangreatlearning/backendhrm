package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Size(min = 2, message = "Employee Name Should be Atleast 2 characters")
    private String empName;

    private String empAddress;

    private double empSalary;


    private long empContactNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date empDOB;

    @Email(message = "Email ID must be valid")
    private String empEmailId;

    @Size(min = 4, message = "Employee Password Should be Atleast 2 characters")
    private String empPassword;


}
