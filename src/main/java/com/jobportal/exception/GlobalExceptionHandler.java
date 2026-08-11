package com.jobportal.exception;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobportal.response.ApiResponse;
import com.jobportal.response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
	public static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){
		logger.error("Resource not Found",ex);
		return ResponseBuilder.error(
                HttpStatus.NOT_FOUND,
                "Resource not found");
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex){
		logger.error("Unexpected application error",ex);
		//ex.printStackTrace();
		return ResponseBuilder.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred");
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String,String>>> handleValidation(MethodArgumentNotValidException ex){
		Map<String, String> errors= new  HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseBuilder.error(
                HttpStatus.BAD_REQUEST,
                "Validation failed",errors);
	}
	@ExceptionHandler(DuplicateApplicationException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateApplication(DuplicateApplicationException ex){
		logger.error("Conflict arises while handaling duplicate application",ex);
		return ResponseBuilder.error(
                HttpStatus.CONFLICT,
                "Duplicate Application");
		
	}
	@ExceptionHandler(OperationNotAllowedException.class)
	public ResponseEntity<ApiResponse<Void>> handleOperationNotAllowed(OperationNotAllowedException ex) {
		logger.error("Opration Not Allwoed",ex);
		return ResponseBuilder.error(
                HttpStatus.FORBIDDEN,
                "Opration not allowed");
	}
}
