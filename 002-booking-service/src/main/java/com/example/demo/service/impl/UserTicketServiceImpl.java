package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.StationDAO;
import com.example.demo.dao.StationRouteDAO;
import com.example.demo.dao.TicketSettingsDAO;
import com.example.demo.dao.TrainDAO;
import com.example.demo.dao.TrainRouteDAO;
import com.example.demo.dao.TrainTimingsDAO;
import com.example.demo.dao.UserTicketDAO;
import com.example.demo.dao.UserTicketDetailsDAO;
import com.example.demo.dto.TicketBookingRequestCreate;
import com.example.demo.dto.TicketBookingResponseCreate;
import com.example.demo.dto.UserTicketDetailsRequestCreate;
import com.example.demo.dto.UserTicketDetailsResponseCreate;
import com.example.demo.dto.UserTicketDetailsResponseList;
import com.example.demo.dto.UserTicketsResponseList;
import com.example.demo.entity.Station;
import com.example.demo.entity.StationRoute;
import com.example.demo.entity.TicketSettings;
import com.example.demo.entity.Train;
import com.example.demo.entity.TrainRoute;
import com.example.demo.entity.UserTicket;
import com.example.demo.entity.UserTicketDetails;
import com.example.demo.exception.AppBaseException;
import com.example.demo.service.UserTicketService;
import com.example.demo.util.DateUtil;
import com.example.demo.util.GeneralUtil;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

@Service
public class UserTicketServiceImpl implements UserTicketService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserTicketServiceImpl.class);
	
	@Autowired
	private TrainDAO trainDAO;
	
	@Autowired
	private TrainRouteDAO trainRouteDAO;
	
	@Autowired
	private UserTicketDAO userTicketDAO;
	
	@Autowired
	TrainTimingsDAO trainTimingsDAO;
	
	@Autowired
	StationRouteDAO stationRouteDAO;
	
	@Autowired
	StationDAO stationDAO;
	
	@Autowired
	TicketSettingsDAO ticketSettingsDAO;
	
	@Autowired
	UserTicketDetailsDAO userTicketDetailsDAO;
	
	@Transactional(readOnly = false)
	public TicketBookingResponseCreate bookTicket(String userId, TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicket()");
		
		// inputs
		String trainRouteId = ticketBookingRequestCreate.getTrainRouteId();
		LocalDateTime travelDate = ticketBookingRequestCreate.getTravelDate();
		int selectedSeats = ticketBookingRequestCreate.getSelectedSeats();
		
		Optional<TrainRoute> trainRouteOptional = trainRouteDAO.findById(trainRouteId);
		
		if(!trainRouteOptional.isPresent())
			throw new AppBaseException("Train Route not found");
		
		TrainRoute trainRoute = trainRouteOptional.get();
		
		Optional<StationRoute> stationRouteOptional = stationRouteDAO.findById(trainRoute.getStationRouteId());
		
		if(!stationRouteOptional.isPresent())
			throw new AppBaseException("StationRoute not found");
		
		StationRoute stationRoute = stationRouteOptional.get();
		
		Optional<Station> fromStationOptional = stationDAO.findById(stationRoute.getFromStationId());
		
		if(!fromStationOptional.isPresent())
			throw new AppBaseException("fromStation not found");
		
		Station fromStation = fromStationOptional.get();
		
		Optional<Station> toStationOptional = stationDAO.findById(stationRoute.getToStationId());
		
		if(!toStationOptional.isPresent())
			throw new AppBaseException("toStation not found");
			
		Station toStation = toStationOptional.get();
		
		Optional<Train> trainOptional = trainDAO.findById(trainRoute.getTrainId());
		
		if(!trainOptional.isPresent())
			throw new AppBaseException("Train not found");
		
		Train train = trainOptional.get();
		int trainOnlineSeats = train.getOnlineSeats();
		
		// check seats availability
		String travelDateString = DateUtil.convertToMysqlFromat(travelDate);
		LOG.debug("generateStatement().travelDateString:" + travelDateString);
		
		Long seatsCountForTicketBooking = userTicketDAO.countSeatsForTicketBooking(trainRouteId, travelDateString);
		LOG.debug("bookTicket().bookedUsersCount:" + seatsCountForTicketBooking);
		
		Long usersCountForTicketBooking = userTicketDAO.countUsersForTicketBooking(trainRouteId, travelDateString);
		LOG.debug("bookTicket().usersCountForTicketBooking:" + usersCountForTicketBooking);
		
		if(usersCountForTicketBooking == null)
			usersCountForTicketBooking = 0l;
		
		if(seatsCountForTicketBooking == null)
			seatsCountForTicketBooking = 0l;
		
		if (seatsCountForTicketBooking >= trainOnlineSeats)
			throw new AppBaseException("All tickets are booked");
		
		if ((seatsCountForTicketBooking + selectedSeats) > trainOnlineSeats)
			throw new AppBaseException("Only " + (trainOnlineSeats - seatsCountForTicketBooking) + " seats are available");
		
		// check journey time
		Long trainRouteAvailableCount = trainTimingsDAO.countByTrainRouteIdAndTrainTime(trainRouteId, travelDateString);
		
		if(trainRouteAvailableCount == null)
			trainRouteAvailableCount = 0l;
		
		if (trainRouteAvailableCount == 0)
			throw new AppBaseException("No tains are available on that specified date");
		
		// get ticketSettingsDAO
		List<TicketSettings> ticketSettingsList = ticketSettingsDAO.findAll();
		
		TicketSettings ticketSettings = ticketSettingsList.get(0);
		
		// generate ticketNumber
		String ticketNoPrefix = ticketSettings.getTicketNoPrefix();
		int ticketNoStartNo = ticketSettings.getTicketNoStartNo();
		int seatNoStartNo = ticketSettings.getSeatNoStartNo();
		String ticketNumber = ticketNoPrefix + (ticketNoStartNo + usersCountForTicketBooking);
		
		// create ticket
		UserTicket userTicket = new UserTicket();
		userTicket.setUserId(userId);
		userTicket.setTrainRouteId(trainRouteId);
		userTicket.setDepartureTime(travelDate);
		userTicket.setTicketNumber(ticketNumber);
		userTicket.setNumberOfSeats(selectedSeats);
		
		// defaults
		userTicket.setId(GeneralUtil.getNewUUID());
		userTicket.setCreatedDate(LocalDateTime.now());
		
		userTicket = userTicketDAO.save(userTicket);
		
		List<UserTicketDetails> userTicketDetailsListCreate = new ArrayList<>();
		
		int seatsBookedCount = 0;
		for (UserTicketDetailsRequestCreate userTicketDetailsRequestCreate : ticketBookingRequestCreate.getUserDetails()) {
			
			UserTicketDetails userTicketDetails = new UserTicketDetails();
			BeanUtils.copyProperties(userTicketDetailsRequestCreate, userTicketDetails);
			
			if (seatsBookedCount >= selectedSeats)
				break;
			
			Long seatNo = seatNoStartNo + seatsCountForTicketBooking + seatsBookedCount;
			userTicketDetails.setId(GeneralUtil.getNewUUID());
			userTicketDetails.setUserTicketId(userTicket.getId());
			userTicketDetails.setSeatNo(seatNo);
			userTicketDetailsListCreate.add(userTicketDetails);
			seatsBookedCount++;
		}
		
		List<UserTicketDetailsResponseCreate> userDetails = new ArrayList<>();
		
		for (UserTicketDetails userTicketDetails : userTicketDetailsListCreate) {
			
			userTicketDetails = userTicketDetailsDAO.save(userTicketDetails);
			LOG.debug("bookTicket().userTicketDetails:" + userTicketDetails);
//			userDetails.add(UserTicketDetailsMapper.INSTANCE.toUserTicketDetailsResponseCreate(userTicketDetails));
			UserTicketDetailsResponseCreate userTicketDetailsResponseCreate = new UserTicketDetailsResponseCreate();
			BeanUtils.copyProperties(userTicketDetails, userTicketDetailsResponseCreate);
			userDetails.add(userTicketDetailsResponseCreate);
		}
		
		TicketBookingResponseCreate response = new TicketBookingResponseCreate();
		response.setTrainNumber(train.getTrainNumber());
		response.setTrainName(train.getName());
		response.setTrainCode(train.getCode());
		response.setJourney(trainRoute.getJourney());
		response.setFromStation(fromStation.getName());
		response.setToStation(toStation.getName());
		response.setDistance(stationRoute.getDistance());
		response.setTicketNumber(userTicket.getTicketNumber());
		response.setDepartureTime(travelDate);
		response.setArrivalTime(travelDate.plusHours(trainRoute.getJourney()));
		response.setSelectedSeats(selectedSeats);
		response.setUserDetails(userDetails);		
		return response;
	}
	
	public List<UserTicketsResponseList> getUserTickets(String userId) {
		LOG.debug("getUserTickets");
		
		List<UserTicket> userTicketList = userTicketDAO.findByUserIdOrderByCreatedDateAsc(userId);
		
		List<UserTicketsResponseList> userTicketsListResponseList = new ArrayList<>();
		
		userTicketList.forEach(userTicket->{
			
			List<UserTicketDetails> userTicketDetailsList = userTicketDetailsDAO.findByUserTicketId(userTicket.getId());
			
//			List<UserTicketDetailsResponseList> userTicketDetailsListDTO = UserTicketDetailsMapper.INSTANCE.toUserTicketDetailsResponseList(userTicketDetailsList);
			List<UserTicketDetailsResponseList> userTicketDetailsListDTO = new ArrayList<>();
			
			userTicketDetailsList.forEach(userTicketDetails->{
				
				UserTicketDetailsResponseList userTicketDetailsResponseList = new UserTicketDetailsResponseList();
				BeanUtils.copyProperties(userTicketDetails, userTicketDetailsResponseList);
				userTicketDetailsListDTO.add(userTicketDetailsResponseList);				
			});
			
			UserTicketsResponseList userTicketsListResponse = new UserTicketsResponseList();
			BeanUtils.copyProperties(userTicket, userTicketsListResponse);
			userTicketsListResponse.setUserDetails(userTicketDetailsListDTO);
			userTicketsListResponseList.add(userTicketsListResponse);
		});
		return userTicketsListResponseList;
	}
}
