package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping("/register")
	public User register(@Valid @RequestBody UserDTO dto) {
		return userService.registerUser(dto);
	}
	@PostMapping("/login")
	public String login(@RequestBody LoginDTO dto) {
		return userService.login(dto);
	}
}
