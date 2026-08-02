package com.jobportal.dto;

import java.time.LocalDateTime;

import com.jobportal.entity.ApplicationStatus;

public class ApplicantDTO {

	private Long applicationId;
	
	private Long candidateId;
	
	private String applicantName;
	
	private String email;
	
	private String resumeUrl;
	
	private ApplicationStatus status;
	
	private LocalDateTime appliedDate;
	
	
}
