package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.demo.enums.EnumsUtil.TrainType;
import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@Entity
@Table(name = "train")
public class Train {
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	private String id;
	
	// Passenger, Rajdhani Express
	@Column(name = "name", length = 30)
	private String name;
	
	// PSR, R_XPR
	@Column(name = "code", nullable = false, length = 20)
	private String code;
	
	// 02692, 07603, 02976
	@Column(name = "train_number", nullable = false, length = 20)
	private String trainNumber;
	
	// PASSENGER, XPRESS
	@Enumerated(EnumType.STRING)
	@Column(name = "train_type", nullable = false, length = 20)
	private TrainType trainType;
	
	@Column(name = "seats", length = 20)
	private int seats;
	
	@Column(name = "online_seats", length = 20)
	private int onlineSeats;
	
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
