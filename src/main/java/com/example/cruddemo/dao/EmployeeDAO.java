package com.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Employee;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer>  {

	
}
