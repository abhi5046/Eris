package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
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
	public Job createJob(@Valid @RequestBody JobDTO jobDTO) {
		return jobService.createJob(jobDTO);
	}
	
	@GetMapping()
	public List<Job> getAllJobs(){
		return jobService.getAllJobs();
	}
	
	@GetMapping("/paginated")
	public Page<Job> getJobs(@RequestParam int page,@RequestParam int size){
		return jobService.getJobsWithPagination(page, size);
	}
	@GetMapping("/paginatedBySal")
	public Page<Job> geJobBySal(@RequestParam int page , @RequestParam int size){
		return jobService.getJobsWithPaginationSortBySal(page, size);
	}
	@GetMapping("/search")
	public List<Job> searchJobs(@RequestParam String keyword){
		return jobService.searchJobs(keyword);
	}
	@GetMapping("/{id}")
	public Job getJobById(@PathVariable Long id) {
		return jobService.getJobById(id);
	}
	@PostMapping("/{id}")
	public Job updateJob(@PathVariable Long id , @RequestBody JobDTO job) {
		return jobService.updateJob(id, job);	
	}
	
	
}
