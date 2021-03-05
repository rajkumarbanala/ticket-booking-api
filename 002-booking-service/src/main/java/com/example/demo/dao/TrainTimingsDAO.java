package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TrainTimings;

/**
 * @author Rajkumar Banala 02-Mar-2021
 *
 */

@Repository
public interface TrainTimingsDAO extends JpaRepository<TrainTimings, String> {
	
	@Query("select count(*) from TrainTimings where trainRouteId=:trainRouteId and date(departureTime)=date(:trainTime) and hour(departureTime)=hour(:trainTime) and minute(departureTime)=minute(:trainTime)")
	public Long countByTrainRouteIdAndTrainTime(String trainRouteId, String trainTime);
}
