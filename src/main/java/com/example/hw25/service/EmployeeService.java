package com.example.hw25.service;

import com.example.hw25.entity.Employee;
import com.example.hw25.exception.EmployeeAlreadyAddedException;
import com.example.hw25.exception.EmployeeNotFoundException;
import com.example.hw25.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

import java.util.*;

@Service
public class EmployeeService {
    private  final List<Employee> employees = new ArrayList<>();
    private final int MAX_SIZE = 2;

    public EmployeeService() {
        employees.add(new Employee("Иван", "Иванов", 1, 50));
        employees.add(new Employee("Петр", "Иванов", 1, 100));
        employees.add(new Employee("Сергей", "Иванов", 2, 150));
        employees.add(new Employee("Степан", "Иванов", 2, 250));
        employees.add(new Employee("Константин", "Иванов", 3, 350));

    }
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + newEmployee + " уже существует");
        }

        employees.add(newEmployee);
        return newEmployee;
    }
    public Employee find(String firstName, String lastName) {
        Employee employeeForFind = new Employee(firstName, lastName);

        if (!employees.contains(employeeForFind)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }

        return employees.get(employees.indexOf(employeeForFind));
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);

        if (!employees.contains(employeeForRemove)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }

        employees.remove(employeeForRemove);
        return employeeForRemove;
    }

    public List<Employee> getAll() {
        return employees;
    }}