package com.example.cruddemo.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	@MockBean
	private EmployeeService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("GET /api/employees/1 - Found")
	void testGetEmployeeByIdFound() throws Exception{
		//Setup our mocked service
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");
		mockEmployee.setId(1);
		doReturn(Optional.of(mockEmployee)).when(service).findById(1);
		
		//execute GET request
		mockMvc.perform(get("/api/employees/{id}",1))
		
			//Validate the response code and content type
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			
			//Validate the body  
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstName", is("John")))
			.andExpect(jsonPath("$.lastName", is( "wick")))
			.andExpect(jsonPath("$.email", is( "test@gmail.com")));	
		
	}
	
	@Test
	@DisplayName("GET /api/employees/1 - Not Found")
	void testGetEmployeeByIdNotFound() throws Exception{
		//Setup our mocked service
		doReturn(Optional.empty()).when(service).findById(1);
		
		//execute GET request
		mockMvc.perform(get("/api/employees/{id}",1))
		
			//Validate the response code and content type
			.andExpect(status().isNotFound());	
	}
	
	@Test
	@DisplayName("POST /api/employees - Create Employee")
	void testCreateEmployee() throws Exception{
		Employee postEmployee = new Employee("John", "wick", "test@gmail.com");
		
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");
		mockEmployee.setId(1);
		
		doReturn(mockEmployee).when(service).save(any());
		
		mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(postEmployee)))
		//Validate the response code and content type
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		
		//Validate the response		
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.firstName", is("John")))
		.andExpect(jsonPath("$.lastName", is( "wick")))
		.andExpect(jsonPath("$.email", is( "test@gmail.com")));	
		
		
	}
	
	@Test
	@DisplayName("PUT /api/employees - Update Employee")
	void testUpdateEmployee() throws Exception{
		Employee putEmployee = new Employee("John", "wick", "test@gmail.com");
		putEmployee.setId(1);
		
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");		
		mockEmployee.setId(1);
		
		//fetch employee mock
		doReturn(Optional.of(mockEmployee)).when(service).findById(1);
		doReturn(mockEmployee).when(service).save(any());
		
		mockMvc.perform(put("/api/employees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(putEmployee)))
		//Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		
		//Validate the response		
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.firstName", is("John")))
		.andExpect(jsonPath("$.lastName", is( "wick")))
		.andExpect(jsonPath("$.email", is( "test@gmail.com")));	
		
		
	}
	
	@Test
	@DisplayName("PUT /api/employees - Update when Employee not found")
	void testUpdateEmployeeNotFound() throws Exception{
		Employee putEmployee = new Employee("John", "wick", "test@gmail.com");
		putEmployee.setId(1);
			
		//fetch employee mock
		doReturn(Optional.empty()).when(service).findById(1);		
		
		mockMvc.perform(put("/api/employees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(putEmployee)))
		//Validate the response code and content type
		.andExpect(status().isNotFound());	
		
		
	}
	
	@Test
	@DisplayName("Delete /api/employees - Delete Employee")
	void testDeleteEmployee() throws Exception{
		Employee deleteEmployee = new Employee("John", "wick", "test@gmail.com");
		deleteEmployee.setId(1);
		
				
		//fetch employee mock
		doReturn(Optional.of(deleteEmployee)).when(service).findById(1);
		doNothing().when(service).deleteById(1);
		
		mockMvc.perform(delete("/api/employees/{id}",1))
		//Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
		
		//Validate the response		
		.andExpect(content().string("Employee Deleted Successfully"));	
		
	}
	
	@Test
	@DisplayName("Delete /api/employees - Delete when Employee not Found")
	void testDeleteEmployeeNotFound() throws Exception{

		//fetch employee mock
		doReturn(Optional.empty()).when(service).findById(1);		
		
		mockMvc.perform(delete("/api/employees/{id}",1))
		//Validate the response code and content type
		.andExpect(status().isNotFound());	
		
	}
	
	@Test
	@DisplayName("GET /api/employees - Found")
	void testGetAllEmployees() throws Exception{
		//Setup our mocked service
		Employee mockEmployee = new Employee("John", "wick", "test@gmail.com");
		mockEmployee.setId(1);
		
		Employee mockEmployee1 = new Employee("Mike", "connor", "test1@gmail.com");
		mockEmployee1.setId(2);
		
		doReturn(Arrays.asList(mockEmployee,mockEmployee1)).when(service).findAll();		
		
		//execute GET request
		mockMvc.perform(get("/api/employees"))
		
			//Validate the response code and content type
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
		
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
}
