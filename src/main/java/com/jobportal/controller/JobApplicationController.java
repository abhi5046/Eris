package com.jobportal.controller;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.dto.UpdateApplicationStatusDTO;
import com.jobportal.response.ApiResponse;
import com.jobportal.response.ResponseBuilder;
import com.jobportal.service.JobApplicationService;

@RestController
@RequestMapping("/application")
public class JobApplicationController {
	
	
	public final JobApplicationService jobApplicationService;
	
	public JobApplicationController(JobApplicationService jobApplicationService) {
		this.jobApplicationService = jobApplicationService;
	}
	@PostMapping("/apply/{jobId}")
	public ResponseEntity<ApiResponse<Void>> applyJob(@PathVariable Long jobId) {
		jobApplicationService.applayForJob(jobId);
		return ResponseBuilder.success("Application submitted successfully",null);
	}
	@GetMapping("/my")
	public ResponseEntity<ApiResponse<List<AppliedJobDTO>>> getMyApplications(){
		List<AppliedJobDTO> applications =jobApplicationService.getMyApplication();
		return  ResponseBuilder.success("Application fetched successfully",applications);
	}
	@PutMapping("/withdraw/{applicationId}")
	public ResponseEntity<ApiResponse<Void>> withdrawApplication(@PathVariable Long applicationId) {
		 jobApplicationService.withdrawApplication(applicationId);
		 
		 return ResponseBuilder.success("Application withdrawl successfully",null);
	}
	@GetMapping("/job/{jobId}/applicants")
	public ResponseEntity<ApiResponse<List<ApplicantDTO>>> getApplicants(@PathVariable Long jobId ){
		List<ApplicantDTO> applicants = jobApplicationService.getMyApplicant(jobId);
		return ResponseBuilder.success("Applicants fetched successfully",applicants);
	}
	@PutMapping("/{applicationId}/status")
	public ResponseEntity<ApiResponse<Void>> updateApplicationStatus(@PathVariable Long applicationId,@RequestBody UpdateApplicationStatusDTO dto) {
		jobApplicationService.updateApplicationStatus(applicationId, dto);
		
		return ResponseBuilder.success("Application Status updated successfully",null);
	}
	
}
