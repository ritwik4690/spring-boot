package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //quick and dirty: inject employee dao
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // get employee by ID
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }

    //add a new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        //if they pass an id in JSON, set id to 0
        //this is to force a save of new item instead of update

        employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }


    //update an employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    //delete employee by id
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        //throw exception if null
        if(employee == null) {
            throw new RuntimeException("Employee not found - "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - "+employeeId;
    }
}
