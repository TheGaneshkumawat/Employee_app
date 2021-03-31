package com.example.cruddemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping()
	public List<Employee> getAllEmployees(){		
		return employeeService.findAll();
	}
	
	@PostMapping()
	public Employee addNew(@Valid @RequestBody Employee employee) {
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping()
	public Employee updateExisting(@Valid @RequestBody Employee emp) {
		Employee employee = employeeService.findById(emp.getId());		
		if(employee == null) {
			throw new RuntimeException("Employee not found with Id - "+emp.getId());
		}	
		employeeService.save(emp);
		return employee;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		Employee employee = employeeService.findById(id);		
		if(employee == null) {
			throw new RuntimeException("Employee not found with Id - "+id);
		}		
		employeeService.deleteById(id);
		return "Employee Deleted Successfully";
	}
	
	@GetMapping("/{id}")
	public Employee getmployee(@PathVariable int id){	
		Employee employee = employeeService.findById(id);		
		if(employee == null) {
			throw new RuntimeException("Employee not found with Id - "+id);
		}		
		return employee;
	}
	
}










