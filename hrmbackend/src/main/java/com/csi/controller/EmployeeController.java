package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import com.csi.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller", description = "This is Controller class for Employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/signup")

    @Operation(summary = "Employee SignUp", description = "Api for Employee SignUp",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {

        log.info("Trying to SignUp For Employee: " + employee.getEmpName());
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);

    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    @Operation(summary = "Employee SignIn", description = "Api for Employee SignIn",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyid/{empId}")
    @Operation(summary = "Employee FindById", description = "Api for Employee Find By Id",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    public ResponseEntity<Optional<Employee>> findById(@PathVariable long empId) {

        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);

    }

    @GetMapping("/findbyname/{empName}")
    @Operation(summary = "Employee Find By Name", description = "Api for Employee get data by its name",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName) {
        return new ResponseEntity<>(employeeService.findByName(empName), HttpStatus.OK);
    }

    @GetMapping("/sortbyid")
    @Operation(summary = "Employee Sort By Id", description = "Api for Employee get data by sorted DESC order",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    public ResponseEntity<List<Employee>> sortByIdDescOrder() {
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpId).reversed()).toList(), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByNameDescOrder() {
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName).reversed()).toList(), HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable long empId, @Valid @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee #ID Does not exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());

        return new ResponseEntity<>(employeeService.update(employee1), HttpStatus.CREATED);
    }

    @PatchMapping("/changeaddress/{empId}")
    public ResponseEntity<Employee> changeAddress(@PathVariable long empId, @RequestParam String empAddress) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee #ID Does not exist"));

        employee1.setEmpAddress(empAddress);

        return new ResponseEntity<>(employeeService.changeAddress(employee1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable long empId) {
        employeeService.deleteById(empId);
        return new ResponseEntity<>("Data Deleted Successfully", HttpStatus.OK);
    }
}
