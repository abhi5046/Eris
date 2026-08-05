package com.jobportal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.dto.UpdateApplicationStatusDTO;
import com.jobportal.response.ApiResponse;
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
		ApiResponse<Void> response = new ApiResponse<>(
				true,
				 "Application submitted successfully",
				 null,
				 LocalDateTime.now());
				
		return ResponseEntity.ok(response);
	}
	@GetMapping("/my")
	public List<AppliedJobDTO> getMyApplications(){
		return jobApplicationService.getMyApplication();
	}
	@PostMapping("/withdraw/{applicationId}")
	public ResponseEntity<ApiResponse<Void>> withdrawApplication(@PathVariable Long applicationId) {
		 jobApplicationService.withdrawApplication(applicationId);
		 ApiResponse<Void> response = new ApiResponse<>(
					true,
					 "Application withdrawl successfully",
					 null,
					 LocalDateTime.now());
		 return ResponseEntity.ok(response);
	}
	@GetMapping("/job/{jobId}/applicants")
	public List<ApplicantDTO> getapplicants(@PathVariable Long jobId ){
		return jobApplicationService.getMyApplicant(jobId);
	}
	@PutMapping("/{applicationId}/status")
	public ResponseEntity<ApiResponse<Void>> updateApplicationStatus(@PathVariable Long applicationId,@RequestBody UpdateApplicationStatusDTO dto) {
		jobApplicationService.updateApplicationStatus(applicationId, dto);
		ApiResponse<Void> response = new ApiResponse<>(
				true,
				 "Application Status updated successfully",
				 null,
				 LocalDateTime.now());
				
		return ResponseEntity.ok(response);
	}
	
}
