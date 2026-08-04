package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;

public interface JobApplicationService {
	
	void applayForJob(Long id);
	
	List<AppliedJobDTO> getMyApplication();
	
	void withdrawApplication(Long applicationId);
	
	List<ApplicantDTO> getMyApplicant(Long jobId);
	
}
