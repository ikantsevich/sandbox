package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.repository.EmployeeRepository;
import com.exadel.sandbox.service.EmployeeService;
import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByID(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee update(Integer id, Employee employee) {
        Employee newEmployee = employeeRepository.getOne(id);

        newEmployee.setEmModified(employee.getEmModified());
        newEmployee.setEmEnd(employee.getEmEnd());
        newEmployee.setEmStart(employee.getEmStart());
        newEmployee.setEmEmail(employee.getEmEmail());
        newEmployee.setEmFirstname(employee.getEmFirstname());
        newEmployee.setEmPosition(employee.getEmPosition());
        newEmployee.setEmLastname(employee.getEmLastname());

        return employeeRepository.save(newEmployee);
    }
}
