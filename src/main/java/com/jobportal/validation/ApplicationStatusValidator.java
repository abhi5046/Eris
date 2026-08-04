package com.jobportal.validation;

import org.springframework.stereotype.Component;

import com.jobportal.entity.ApplicationStatus;
import com.jobportal.exception.OperationNotAllowedException;

@Component
public class ApplicationStatusValidator {
	public void validate(ApplicationStatus currentStatus,
            ApplicationStatus newStatus) {
	switch (currentStatus) {
	case APPLIED:
		if(newStatus!=ApplicationStatus.SHORTLISTED && newStatus != ApplicationStatus.REJECTED){
			throw new OperationNotAllowedException(
			        "Application can only be SHORTLISTED or REJECTED.");
		}
		break;
		
	case SHORTLISTED:
		if(newStatus!=ApplicationStatus.HIRED && newStatus != ApplicationStatus.REJECTED){
			throw new OperationNotAllowedException(
				        "Shortlisted candidate can only be HIRED or REJECTED..");
		}
		break;
	case HIRED:
	case REJECTED:
	case WITHDRAWN:
	
	    throw new OperationNotAllowedException(
	            "Application status cannot be changed.");
	
	default:
	
	    throw new OperationNotAllowedException(
	            "Invalid application status.");
	}
  }
}
