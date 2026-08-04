package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.dto.UpdateApplicationStatusDTO;
import com.jobportal.service.JobApplicationService;

@RestController
@RequestMapping("/application")
public class JobApplicationController {
	
	@Autowired
	public JobApplicationService jobApplicationService;
	
	@PostMapping("/apply/{jobId}")
	public String applyJob(@PathVariable Long jobId) {
		jobApplicationService.applayForJob(jobId);
		return "Application submitted successfully";
	}
	@GetMapping("/my")
	public List<AppliedJobDTO> getMyApplications(){
		return jobApplicationService.getMyApplication();
	}
	@PostMapping("/withdraw/{applicationId}")
	public String withdrawApplication(@PathVariable Long applicationId) {
		 jobApplicationService.withdrawApplication(applicationId);
		 return "Application withdrawn successfully";
	}
	@GetMapping("/job/{jobId}/applicants")
	public List<ApplicantDTO> getapplicants(@PathVariable Long jobId ){
		return jobApplicationService.getMyApplicant(jobId);
	}
	@PostMapping("/{applicationId}/status")
	public String updateApplicationStatus(@PathVariable Long applicationId,@RequestBody UpdateApplicationStatusDTO dto) {
		jobApplicationService.updateApplicationStatus(applicationId, dto);
		return "Application Status updated successfully";
	}
	
}
