package org.example.employeesystem.service;

import org.example.employeesystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(Long id);

    Employee save(Employee employee);

    Employee update(Long id, Employee employee);

    void deleteById(Long id);
}
