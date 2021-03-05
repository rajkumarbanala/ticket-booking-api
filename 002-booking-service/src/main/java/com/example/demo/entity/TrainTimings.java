package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "train_timings")
public class TrainTimings {
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	private String id;
	
	@Column(name = "train_route_id", nullable = false, length = 50)
	private String trainRouteId;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "departure_time", nullable = false, length = 50)
	private LocalDateTime departureTime;
	
	/** audit info */
	@Version
	@Column(name = "version")
	private Long version;

	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;
}
