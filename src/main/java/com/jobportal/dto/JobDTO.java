package com.jobportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobDTO {
	@NotBlank(message = "Title is required")
	private String title;
	@NotBlank(message = "Description is required")
	private String description;
	@NotBlank(message = "Location is required")
	private String location;
	@NotNull(message = "Salary is required")
	private Double salary;
	@NotBlank(message = "Company Name is required")
	private String companyName;
}
