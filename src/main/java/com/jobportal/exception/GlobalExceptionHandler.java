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

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){

		return ResponseBuilder.error(
                HttpStatus.NOT_FOUND,
                ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex){
		ex.printStackTrace();
		return ResponseBuilder.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage());
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

		return ResponseBuilder.error(
                HttpStatus.CONFLICT,
                ex.getMessage());
		
	}
	@ExceptionHandler(OperationNotAllowedException.class)
	public ResponseEntity<ApiResponse<Void>> handleOperationNotAllowed(OperationNotAllowedException ex) {

		return ResponseBuilder.error(
                HttpStatus.FORBIDDEN,
                ex.getMessage());
	}
}
