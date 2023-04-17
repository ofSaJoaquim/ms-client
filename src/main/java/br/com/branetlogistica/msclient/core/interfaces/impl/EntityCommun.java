package br.com.branetlogistica.msclient.core.interfaces.impl;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntityCommun {
	
	@ColumnDefault(value = "false")
	@Column(nullable = false)	
	protected boolean disabled;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	protected LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.disabled = false;
	}
	
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

}
