package com.jobportal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;

public interface JobService {
	Job createJob(JobDTO jobDTO);
	List<Job> getAllJobs();
	Page<Job> getJobsWithPagination(int page, int size);
	public Page<Job> getJobsWithPaginationSortBySal(int page, int size);
	List<Job> searchJobs(String keyword);
	public Job getJobById(Long id);
	Job updateJob(Long id,JobDTO dto);
}

