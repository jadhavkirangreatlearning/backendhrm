package com.csi.repository;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom Method
    Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

    // JPAQL
    @Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :empName, '%'))")
    List<Employee> findByEmpNameIgnoreCase(@Param("empName") String empName);
}
