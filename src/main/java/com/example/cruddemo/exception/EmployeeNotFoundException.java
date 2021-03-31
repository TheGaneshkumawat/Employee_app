package com.example.cruddemo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String error) {
		super(error);
	}
}
