package com.jobportal.dto;

import java.time.LocalDateTime;

import com.jobportal.entity.ApplicationStatus;

import lombok.Data;

@Data
public class AppliedJobDTO {
	
    private Long applicationId;
    
    private Long jobId;
    
    private String title;
    
    private String companyName;
    
    private String location;
    
    private ApplicationStatus status;
    
    private LocalDateTime appliedDate;
}
