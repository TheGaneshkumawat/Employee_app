package com.example.cruddemo.dao;

import java.util.List;
import java.util.Optional;

import com.example.cruddemo.entity.Employee;

public interface EmployeeDAOHIb {

	public List<Employee> findAll();

	public Optional<Employee> findById(int id);
	public void save(Employee employee);
	public void deleteById(int id);
}
