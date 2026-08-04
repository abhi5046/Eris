package com.jobportal.mapper;

import org.springframework.stereotype.Component;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.entity.JobApplication;

@Component
public class JobApplicationMapper {
	
	public AppliedJobDTO toAppliedJobDTO(JobApplication application) {
		AppliedJobDTO dto = new AppliedJobDTO();
		dto.setApplicationId(application.getId());
		dto.setJobId(application.getJob().getId());
		dto.setTitle(application.getJob().getTitle());
        dto.setCompanyName(application.getJob().getCompanyName());
        dto.setLocation(application.getJob().getLocation());
        dto.setStatus(application.getStatus());
        dto.setAppliedDate(application.getCreatedAt());
		return dto;
	}
	
	public ApplicantDTO toApplicantDTO(JobApplication application) {
		ApplicantDTO dto=new ApplicantDTO();
		dto.setApplicantName(application.getCandidate().getName());
		dto.setApplicationId(application.getId());
		dto.setAppliedDate(application.getCreatedAt());
		dto.setCandidateId(application.getCandidate().getId());
		dto.setEmail(application.getCandidate().getEmail());
		dto.setStatus(application.getStatus());
		return dto;
	}
	
}
