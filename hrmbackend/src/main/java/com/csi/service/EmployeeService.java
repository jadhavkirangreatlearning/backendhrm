package com.csi.service;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

        if (employee != null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
            flag = true;

        }
        return flag;
    }

    @Cacheable(value = "empId")
    @Override
    public Optional<Employee> findById(long empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public List<Employee> findByName(String empName) {
        return employeeRepository.findByEmpNameIgnoreCase(empName);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee changeAddress(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(long empId) {
        employeeRepository.deleteById(empId);
    }
}
