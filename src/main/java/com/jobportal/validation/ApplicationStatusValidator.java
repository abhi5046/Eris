package com.jobportal.validation;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jobportal.entity.ApplicationStatus;
import com.jobportal.exception.OperationNotAllowedException;

@Component
public class ApplicationStatusValidator {
	public final Map<ApplicationStatus,Set<ApplicationStatus>> allowedTransitions =
		Map.of(
				ApplicationStatus.APPLIED,Set.of(ApplicationStatus.SHORTLISTED,ApplicationStatus.REJECTED),
				ApplicationStatus.SHORTLISTED,Set.of(ApplicationStatus.HIRED,ApplicationStatus.REJECTED),
				ApplicationStatus.HIRED,Set.of(),
				ApplicationStatus.REJECTED,Set.of(),
				ApplicationStatus.WITHDRAWN,Set.of()
				);
	
	public void validate(ApplicationStatus currentStatus,
            ApplicationStatus newStatus) {
	Set<ApplicationStatus> allowedStatuses=
			allowedTransitions.get(currentStatus);
	if(allowedStatuses == null ||
            !allowedStatuses.contains(newStatus)) {
		throw new OperationNotAllowedException("Application cannot be changed from "+currentStatus+" to "+newStatus);
	}
  }
}
