package com.jobportal.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
	public static <T> ResponseEntity<ApiResponse<T>> success(String message ,T data){
		ApiResponse<T> response= new ApiResponse<>(
				true,
                message,
                data,
                LocalDateTime.now());
		return ResponseEntity.ok(response);
	}
	
	public static <T> ResponseEntity<ApiResponse<T>> error(
            HttpStatus status,
            String message) {

        ApiResponse<T> response = new ApiResponse<>(
                false,
                message,
                null,
                LocalDateTime.now());

        return ResponseEntity
                .status(status)
                .body(response);
    }
	public static <T> ResponseEntity<ApiResponse<T>> error(
            HttpStatus status,
            String message,T data) {

        ApiResponse<T> response = new ApiResponse<>(
                false,
                message,
                data,
                LocalDateTime.now());

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
