package com.example.cruddemo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {

	@MockBean
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private EmployeeService service;
	
	@Test
	@DisplayName("Find employee")
	void testFindById() {
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");
		mockEmployee.setId(1);
		
		doReturn(Optional.of(mockEmployee)).when(employeeDAO).findById(1);
		
		Optional<Employee> optionalEmp = service.findById(1);
		
		System.out.println(optionalEmp.get().toString());
		assertNotNull(optionalEmp.get(), "Employee not Found.");
		assertTrue(optionalEmp.get().getId()== 1,"Expected employee not found");
		
	}
	
	
	@Test
	@DisplayName("Find all employees")
	void testFindAll() {
		Employee mockEmployee1 = new Employee("John", "wick", "test@gmail.com");
		mockEmployee1.setId(1);
		
		Employee mockEmployee2 = new Employee("Mike", "connor", "test1@gmail.com");
		mockEmployee2.setId(2);
		
		doReturn(Arrays.asList(mockEmployee1,mockEmployee2)).when(employeeDAO).findAll();
		
		List<Employee> employess = service.findAll();
		
		assertNotNull(employess, "Employee not Found.");
		assertTrue(employess.size()== 2,"Expected employees not found");
		
	}
	
	@Test
	@DisplayName("Create new employee")
	void testSave() {
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");
		
		doReturn(mockEmployee).when(employeeDAO).save(mockEmployee);
		
		Employee employee = service.save(mockEmployee);
		
		assertNotNull(employee, "Employee not created.");
		assertTrue(employee.getFirstName()=="John","Expected employee not found");
		
	}
	
	@Test
	@DisplayName("Delete existing employee")
	void testDelete() {		
		
		doNothing().when(employeeDAO).deleteById(1);		
		service.deleteById(1);	
		
	}
}
