package com.example.cruddemo.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cruddemo.entity.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeDAOTest {

	@Autowired
	private EmployeeDAO employeeDao;
	
	@Test
	@DisplayName("save an employee")
	public void testCreateEmployee() {
		Employee employee = new Employee("John", "Smith", "test@gmail.com");
		employeeDao.save(employee);	
		assertNotNull(employee.getId());
	}
	
	@Test
	@DisplayName("Delete an employee")
	public void testDeleteEmployee() {
		Employee employee = new Employee("John", "Smith", "test@gmail.com");
		employeeDao.save(employee);	

		int id = employee.getId();
		
		employeeDao.deleteById(id);
		
		 Assertions.assertThrows(JpaObjectRetrievalFailureException.class, () -> {
		    employeeDao.getOne(id);
		  });
	}
	
	@Test
	@DisplayName("Find All employees")
	public void testFindAll() {
		Employee mockEmployee1 = new Employee("John", "wick", "test@gmail.com");
		
		Employee mockEmployee2 = new Employee("Mike", "connor", "test1@gmail.com");
		employeeDao.save(mockEmployee1);	
		employeeDao.save(mockEmployee2);	
		
		List<Employee> employees = employeeDao.findAll();
		Assertions.assertEquals(2, employees.size());
		
	}
	
	
	
	
}
