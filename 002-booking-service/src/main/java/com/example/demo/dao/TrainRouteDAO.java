package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TrainRoute;

/**
 * @author Rajkumar Banala 02-Mar-2021
 *
 */

@Repository
public interface TrainRouteDAO extends JpaRepository<TrainRoute, String> {
	
	@Query("select count(*) from TrainRoute where id=:id and date(trainTimings)=date(:journeyTime) and hour(trainTimings)=hour(:journeyTime) and minute(trainTimings)=minute(:journeyTime)")
	public Long countByIdAndJourneyTime(String id, String journeyTime);
}
