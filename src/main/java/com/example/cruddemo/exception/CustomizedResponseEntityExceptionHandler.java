package com.example.cruddemo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cruddemo.utility.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * @ExceptionHandler(Exception.class)
	 * 
	 * @ResponseBody public final ResponseEntity<Object>
	 * handleAllExceptions(Exception ex, WebRequest request) { ErrorDetails
	 * exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity(exceptionResponse,
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseBody
  public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
	  ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }
	
	/*
	 * @Override
	 * 
	 * @ResponseBody protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) { ErrorDetails errorDetails =
	 * new ErrorDetails(new Date(), "Validation Failed",
	 * ex.getBindingResult().toString()); return new ResponseEntity(errorDetails,
	 * HttpStatus.BAD_REQUEST); }
	 */
}