package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "station_route")
public class StationRoute {
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	private String id;
	
	// refers to station table
	@Column(name = "from_station_id", nullable = false, length = 50)
	private String fromStationId;
	
	// refers to station table
	@Column(name = "to_station_id", nullable = false, length = 50)
	private String toStationId;
	
	// distance in km ie; 400km, 500km
	@Column(name = "distance", length = 20)
	private Long distance;
	
	/** audit info */
	@Version
	@Column(name = "version")
	private Long version;

	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;

}
