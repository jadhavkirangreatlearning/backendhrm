package com.csi.service;

import com.csi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    Optional<Employee> findById(long empId);

    List<Employee> findByName(String empName);

    List<Employee> findAll();

    Employee update(Employee employee);

    Employee changeAddress(Employee employee);

    void deleteById(long empId);


}
