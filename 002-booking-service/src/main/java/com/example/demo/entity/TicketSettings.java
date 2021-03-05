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
@Table(name = "ticket_settings")
public class TicketSettings {
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	private String id;
	
	@Column(name = "name", length = 30)
	private String ticketNoPrefix;
	
	@Column(name = "ticket_no_start_no", length = 30)
	private int ticketNoStartNo;
	
	@Column(name = "seat_no_start_no", length = 30)
	private int seatNoStartNo;
	
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
