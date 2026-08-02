package com.jobportal.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
	
	Optional<JobApplication> findByJob_IdAndCandidate_Id(Long jobId, Long candidateId);
	
	List<JobApplication> findByCandidate_Id(Long candidateId);
	
	List<JobApplication> findByJob_Id(Long jobId);
	
	
}
