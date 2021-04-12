package com.example.cruddemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	public List<Employee> findAll() {		
		 Iterable<Employee> findAll = employeeDAO.findAll();
		 List<Employee> result = new ArrayList<Employee>();
		 findAll.forEach(result::add);
		 return result;
		 
	}

	public Optional<Employee> findById(int id) {		
		return employeeDAO.findById(id);
	}
	
	@Transactional
	public Employee save(Employee employee) {		
		employeeDAO.save(employee);
		return employee;
	}
	
	@Transactional
	public void deleteById(int id) {		
		employeeDAO.deleteById(id);
	}
	

}
