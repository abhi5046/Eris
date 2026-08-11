package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.response.ApiResponse;
import com.jobportal.response.ResponseBuilder;
import com.jobportal.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	
	private final JobService jobService;
	 
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Job>> createJob(@Valid @RequestBody JobDTO jobDTO) {
		Job job=jobService.createJob(jobDTO);
		return ResponseBuilder.success("Job Created Sucessfully", job);
	}
	
	@GetMapping()
	public ResponseEntity<ApiResponse<List<Job>>> getAllJobs(){
		List<Job> job=jobService.getAllJobs();
		return ResponseBuilder.success("All Job fetched Sucessfully", job);
	}
	
	@GetMapping("/paginated")
	public ResponseEntity<ApiResponse<Page<Job>>> getJobs(@RequestParam int page,@RequestParam int size){
		Page<Job> job=jobService.getJobsWithPagination(page, size);
		return ResponseBuilder.success("Job fetched Sucessfully", job);
	}
	@GetMapping("/paginatedBySal")
	public ResponseEntity<ApiResponse<Page<Job>>> geJobBySal(@RequestParam int page , @RequestParam int size){
		Page<Job> job=jobService.getJobsWithPaginationSortBySal(page, size);
		return ResponseBuilder.success("Job filtered by salary fetched Sucessfully", job);
	}
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Job>>>searchJobs(@RequestParam String keyword){
		List<Job> job=jobService.searchJobs(keyword);
		return ResponseBuilder.success("Searched Job fetched Sucessfully", job);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long id) {
		Job job=jobService.getJobById(id);
		return ResponseBuilder.success("Job fetched Sucessfully", job);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Job>> updateJob(@PathVariable Long id , @RequestBody JobDTO job) {
		Job jobs=jobService.updateJob(id, job);
		return 	ResponseBuilder.success("Job Updated Sucessfully", jobs);
	}
	
	
}
