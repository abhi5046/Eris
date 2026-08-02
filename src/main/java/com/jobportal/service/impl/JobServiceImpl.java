package com.jobportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jobportal.config.JwtUtil;
import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobService;
import com.jobportal.util.SecurityUtil;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	

	@Override
	public Job createJob(JobDTO jobDTO) {
		Job job=new Job();
		job.setTitle(jobDTO.getTitle());
		job.setDescription(jobDTO.getDescription());
		job.setLocation(jobDTO.getLocation());
		job.setSalary(jobDTO.getSalary());
		job.setCompanyName(jobDTO.getCompanyName());
		
		String email = SecurityUtil.getCurrentUserEmail();

		User user = userRepository.findByEmail(email)
		        .orElseThrow(() ->
		                new RuntimeException("User not found"));

		job.setCreatedBy(user);

		return jobRepository.save(job);
	}

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public Page<Job> getJobsWithPagination(int page, int size) {
		return jobRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Job> getJobsWithPaginationSortBySal(int page, int size) {
		return jobRepository.findAll(PageRequest.of(page, size,Sort.by("salary").descending()));
	}

	@Override
	public List<Job> searchJobs(String keyword) {
		return jobRepository.findByTitleContainingIgnoreCase(keyword);
	}

	@Override
	public Job getJobById(Long id) {
		
		return jobRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Job Not Found With ID:"+id));
	}

	@Override
	public Job updateJob(Long id, JobDTO dto) {
		Job job=jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job not found"));
		String email=SecurityUtil.getCurrentUserEmail();
		// ownership check
		if(!job.getCreatedBy().getEmail().equals(email)) {
			throw new RuntimeException("You are not allowed to update this job");
		}
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setLocation(dto.getLocation());
		job.setSalary(dto.getSalary());
		
		return jobRepository.save(job);
	}

	
	
	
}
