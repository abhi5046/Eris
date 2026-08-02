package com.jobportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@EnableMethodSecurity
public class AdminController {
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String dashboared() {
		return "welcome admin";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/stats")
	public String stats() {
	    return "Only admin can access";
	}
}
