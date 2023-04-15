package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    //define field for entitymanager
    private EntityManager entityManager;

    //setup constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        //return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get employee
        Employee employee = entityManager.find(Employee.class, theId);

        //return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        //save employee
        Employee dbEmployee = entityManager.merge(employee);

        //return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        //find employee by id
        Employee employee = entityManager.find(Employee.class, theId);

        //remove employee
        entityManager.remove(employee);

    }
}
