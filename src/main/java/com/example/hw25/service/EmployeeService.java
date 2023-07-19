package com.example.hw25.service;

import com.example.hw25.entity.Employee;
import com.example.hw25.exception.EmployeeAlreadyAddedException;
import com.example.hw25.exception.EmployeeNotFoundException;
import com.example.hw25.exception.EmployeeStorageIsFullException;
import com.example.hw25.exception.InvalidNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.naming.InterruptedNamingException;
import java.util.ArrayList;

import java.util.List;

import java.util.*;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private  final List<Employee> employees = new ArrayList<>();
    private final int MAX_SIZE = 2;

      public Employee add(String firstName, String lastName){
          checkUpperCase(firstName, lastName);
          checkIsAlpha(firstName, lastName);

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

    private void checkUpperCase(String firstName, String lastName) {
        String firstNameInUpperCase = StringUtils.capitalize(firstName);
        String lastNameInUpperCase = StringUtils.capitalize(lastName);
        if (!(firstName.equals(firstNameInUpperCase))) {
            throw new InvalidNameException("Имя начинается не с заглавной буквы");
        }
        if (!(lastName.equals(lastNameInUpperCase))) {
            throw new InvalidNameException("Фамилия начинается не с заглавной буквы");
        }
    }

    private void checkIsAlpha(String firstName, String lastName) {
        if (StringUtils.isAlpha(firstName)) {
            throw new InvalidNameException("Имя содержит запрещенные символы");}
            if (StringUtils.isAlpha(lastName)) {
                throw new InvalidNameException("Фамилия содержит запрещенные символы");
        }
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