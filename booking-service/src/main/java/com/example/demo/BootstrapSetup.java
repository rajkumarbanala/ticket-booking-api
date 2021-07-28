package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StationDAO;
import com.example.demo.dao.StationRouteDAO;
import com.example.demo.dao.TicketSettingsDAO;
import com.example.demo.dao.TrainDAO;
import com.example.demo.dao.TrainRouteDAO;
import com.example.demo.dao.TrainTimingsDAO;
import com.example.demo.entity.Station;
import com.example.demo.entity.StationRoute;
import com.example.demo.entity.TicketSettings;
import com.example.demo.entity.Train;
import com.example.demo.entity.TrainRoute;
import com.example.demo.entity.TrainTimings;
import com.example.demo.enums.EnumsUtil.StationStatus;
import com.example.demo.enums.EnumsUtil.TrainType;
import com.example.demo.util.GeneralUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

@Slf4j
@Service
public class BootstrapSetup {
	
	@Autowired
	StationDAO stationDAO;
	
	@Autowired
	StationRouteDAO stationRouteDAO;
	
	@Autowired
	TrainDAO trainDAO;
	
	@Autowired
	TrainRouteDAO trainRouteDAO;
	
	@Autowired
	TrainTimingsDAO trainTimingsDAO;
	
	@Autowired
	TicketSettingsDAO ticketSettingsDAO;
	
	@Value("${com.example.demo.db.data-setup}")
	private boolean dbDataSetup;
	
	public void setup() {
		log.debug("setup()");
		
		if(!dbDataSetup)
			return;
		
		// ticket settings
		TicketSettings ticketSettings = new TicketSettings();
		ticketSettings.setId(GeneralUtil.getNewUUID());
		ticketSettings.setTicketNoPrefix("TK-");
		ticketSettings.setTicketNoStartNo(100);
		ticketSettings.setSeatNoStartNo(1000);
		ticketSettingsDAO.save(ticketSettings);
		
		// stations
		Station station1 = new Station();
		station1.setId(GeneralUtil.getNewUUID());
		station1.setCode("HYD");
		station1.setName("Hyderabad");
		station1.setStatus(StationStatus.ACTIVE);
		
		station1 = stationDAO.save(station1);
		
		Station station2 = new Station();
		station2.setId(GeneralUtil.getNewUUID());
		station2.setCode("BNG");
		station2.setName("Benguluru");
		station2.setStatus(StationStatus.ACTIVE);
		
		station2 = stationDAO.save(station2);
		
		// station routes
		StationRoute stationRoute1 = new StationRoute();
		stationRoute1.setId(GeneralUtil.getNewUUID());
		stationRoute1.setFromStationId(station1.getId());
		stationRoute1.setToStationId(station2.getId());
		stationRoute1.setDistance(400l);
		
		stationRoute1 = stationRouteDAO.save(stationRoute1);
		
		// trains
		Train train1 = new Train();
		train1.setId(GeneralUtil.getNewUUID());
		train1.setName("Passenger");
		train1.setCode("PSR");
		train1.setTrainNumber("12345");
		train1.setTrainType(TrainType.PASSENGER);
		train1.setSeats(50);
		train1.setOnlineSeats(40);
		
		Train train2 = new Train();
		train2.setId(GeneralUtil.getNewUUID());
		train2.setName("Rajdhani Express");
		train2.setCode("R_XPR");
		train2.setTrainNumber("654321");
		train2.setTrainType(TrainType.XPRESS);
		train2.setSeats(50);
		train2.setOnlineSeats(40);
		
		train1 = trainDAO.save(train1);
		trainDAO.save(train2);
		
		// train routes
		TrainRoute trainRoute1 = new TrainRoute();
		trainRoute1.setId(GeneralUtil.getNewUUID());
		trainRoute1.setTrainId(train1.getId());
		trainRoute1.setStationRouteId(stationRoute1.getId());
		trainRoute1.setJourney(12);

		trainRouteDAO.save(trainRoute1);
		
		// train routes
		TrainRoute trainRoute2 = new TrainRoute();
		trainRoute2.setId(GeneralUtil.getNewUUID());
		trainRoute2.setTrainId(train2.getId());
		trainRoute2.setStationRouteId(stationRoute1.getId());
		trainRoute2.setJourney(12);

		trainRouteDAO.save(trainRoute2);
		
		// train timings
		LocalDateTime localDateTime1 = LocalDate.now().atTime(17, 25, 0);
		localDateTime1 = localDateTime1.plusDays(6);
		
		TrainTimings trainTimings1 = new TrainTimings();
		trainTimings1.setId(GeneralUtil.getNewUUID());
		trainTimings1.setTrainRouteId(trainRoute1.getId());
		trainTimings1.setDepartureTime(localDateTime1);
		trainTimingsDAO.save(trainTimings1);

		LocalDateTime localDateTime2 = LocalDate.now().atTime(17, 25, 0);
		localDateTime2 = localDateTime2.plusDays(8);
		trainTimings1 = new TrainTimings();
		trainTimings1.setId(GeneralUtil.getNewUUID());
		trainTimings1.setTrainRouteId(trainRoute1.getId());
		trainTimings1.setDepartureTime(localDateTime2);
		trainTimingsDAO.save(trainTimings1);
		
		LocalDateTime localDateTime3 = LocalDate.now().atTime(10, 15, 0);
		localDateTime3 = localDateTime3.plusDays(15);
		TrainTimings trainTimings3 = new TrainTimings();
		trainTimings3.setId(GeneralUtil.getNewUUID());
		trainTimings3.setTrainRouteId(trainRoute2.getId());
		trainTimings3.setDepartureTime(localDateTime3);
		trainTimingsDAO.save(trainTimings3);
	}

}
