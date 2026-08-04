package com.jobportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.AppliedJobDTO;
import com.jobportal.dto.UpdateApplicationStatusDTO;
import com.jobportal.entity.ApplicationStatus;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.Role;
import com.jobportal.entity.User;
import com.jobportal.exception.DuplicateApplicationException;
import com.jobportal.exception.OperationNotAllowedException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.mapper.JobApplicationMapper;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobApplicationService;
import com.jobportal.util.SecurityUtil;
import com.jobportal.validation.ApplicationStatusValidator;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobApplicationRepository applicationRepository;

	@Autowired
	private JobApplicationMapper jobApplicationMapper;

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ApplicationStatusValidator statusValidator;

	@Override
	public void applayForJob(Long id) throws RuntimeException {
		String email = SecurityUtil.getCurrentUserEmail();

		User candidate = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));
		Optional<JobApplication> existing = applicationRepository.findByJob_IdAndCandidate_Id(id, candidate.getId());
		if (existing.isPresent()) {
			throw new DuplicateApplicationException("You have already applied for this job.");
		}

		if (candidate.getRole() != Role.CANDIDATE) {
			throw new OperationNotAllowedException("Only candidates can apply for jobs");
		}

		JobApplication application = new JobApplication();
		application.setCandidate(candidate);
		application.setJob(job);
		application.setStatus(ApplicationStatus.APPLIED);
		applicationRepository.save(application);
	}

	@Override
	public List<AppliedJobDTO> getMyApplication() {
		String email = SecurityUtil.getCurrentUserEmail();

		User candidate = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		List<JobApplication> applications = applicationRepository.findByCandidate_Id(candidate.getId());

		return applications.stream().map(jobApplicationMapper::toAppliedJobDTO).toList();
	}

	@Override
	public void withdrawApplication(Long applicationId) {
		String email = SecurityUtil.getCurrentUserEmail();

		User candidate = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		JobApplication application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application Not Found"));

		if (application.getCandidate().getId() != candidate.getId()) {
			throw new OperationNotAllowedException("You cannot withdraw another candidate's application.");
		}

		if (application.getStatus() == ApplicationStatus.WITHDRAWN) {
			throw new OperationNotAllowedException("Application is already withdrawn.");
		}

		application.setStatus(ApplicationStatus.WITHDRAWN);

		applicationRepository.save(application);

	}

	@Override
	public List<ApplicantDTO> getMyApplicant(Long jobId) {
		String email = SecurityUtil.getCurrentUserEmail();
		User recruiter = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not available"));// sorry
																														// i
																														// am
																														// unable
																														// to
																														// understand
																														// how
																														// to
																														// find
																														// rucruter
		User owner = job.getCreatedBy();
		if (!owner.getId().equals(recruiter.getId())) {
			throw new OperationNotAllowedException("You are not allowed to view applicants of this job.");
		}

		List<JobApplication> applications = applicationRepository.findByJob_Id(jobId);

		return applications.stream().map(jobApplicationMapper::toApplicantDTO).toList();
	}

	@Override
	public void updateApplicationStatus(Long applicationId, UpdateApplicationStatusDTO dto) {
		JobApplication application=applicationRepository.findById(applicationId).orElseThrow(()->new ResourceNotFoundException("Application Not Found"));
		
		String email=SecurityUtil.getCurrentUserEmail();
		User recruiter=userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
		User Owner=application.getJob().getCreatedBy();
		if(!recruiter.getId().equals(Owner.getId())) {
			throw new OperationNotAllowedException("You are not allowed to update status of this job.");
		}
		ApplicationStatus currentStatus=application.getStatus();
		ApplicationStatus newStatus=dto.getStatus();
		
		if(currentStatus== ApplicationStatus.HIRED
		        || currentStatus == ApplicationStatus.REJECTED
		        || currentStatus == ApplicationStatus.WITHDRAWN) {
			throw new OperationNotAllowedException(
			        "Application status cannot be changed.");
		}
		
		
		statusValidator.validate(currentStatus, newStatus);
		application.setStatus(newStatus);
		applicationRepository.save(application);
	}

}
