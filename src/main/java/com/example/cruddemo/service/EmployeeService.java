package com.example.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	public List<Employee> findAll() {		
		return employeeDAO.findAll();
	}

	public Employee findById(int id) {		
		return employeeDAO.findById(id);
	}
	
	@Transactional
	public void save(Employee employee) {		
		employeeDAO.save(employee);
	}
	
	@Transactional
	public void deleteById(int id) {		
		employeeDAO.deleteById(id);
	}
	

}
