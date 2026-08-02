package com.jobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	@PrePersist
	public void onCreate() {
		createdAt=LocalDateTime.now();
	}
	@PreUpdate
	public void onUpdate() {
		updatedAt=LocalDateTime.now();
	}
}
