package com.testing.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
@MappedSuperclass
public class Basetimes {
	@Column(nullable=false, updatable=false)
	private LocalDate createdDate;
	private String createdBy;
	private LocalDate updatedDate;
	private String updatedBy;
	
	@PrePersist
	public void prePersist() {
		createdDate = updatedDate = LocalDate.now();
	}
	@PreUpdate
	public void preUpdate() {
		updatedDate = LocalDate.now();
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
