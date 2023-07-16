package com.example.hw25.controller;

import com.example.hw25.entity.Employee;
import com.example.hw25.service.DepartmentService;
import com.example.hw25.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/departments")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee withMaxSalary(@RequestParam int departmentId) {
        return departmentService.withMaxSalary(departmentId);
    }
    @GetMapping("min-salary")
    public Employee withMinSalary(@RequestParam int departmentId) {
        return departmentService.withMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeeByDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentService.employeesByDepartment(departmentId);
    }

}
