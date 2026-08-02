package com.jobportal.dto;

import com.jobportal.entity.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
	
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Invalid Email")
	private String email;
	@NotBlank(message = "password is reqired")
	private String password;
	
	private Role role;
}
