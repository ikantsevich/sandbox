package com.exadel.sandbox.service;

import com.exadel.sandbox.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee getEmployeeByID(Integer id);

    Employee create(Employee employee);

    void deleteById(Integer id);

    Employee update(Integer id, Employee employee);
}
