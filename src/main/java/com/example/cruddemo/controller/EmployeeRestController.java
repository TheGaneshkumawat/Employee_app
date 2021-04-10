package com.example.cruddemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.exception.EmployeeNotFoundException;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addNew(@Valid @RequestBody Employee employee) {
		employee.setId(0);
		return employeeService.save(employee);		
	}
	
	@PutMapping()
	public Employee updateExisting(@Valid @RequestBody Employee emp) {
		Optional<Employee> employee = employeeService.findById(emp.getId());		
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with Id - "+emp.getId());
		}	
		employeeService.save(emp);
		return employee.get();
	}
	
	@DeleteMapping(path = "/{id}",produces = {MediaType.TEXT_PLAIN_VALUE})
	public String delete(@PathVariable int id) {
		Optional<Employee> employee = employeeService.findById(id);		
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with Id - "+id);
		}			
		employeeService.deleteById(id);
		return "Employee Deleted Successfully";
	}
	
	@GetMapping("/{id}")
	public Employee getmployee(@PathVariable int id){	
		Optional<Employee> employee = employeeService.findById(id);		
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with Id - "+id);
		}		
		return employee.get();
	}
	
}










