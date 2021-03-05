//package com.example.demo.mappers;
//
//import java.util.List;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import com.example.demo.dto.UserTicketDetailsRequestCreate;
//import com.example.demo.dto.UserTicketDetailsResponseCreate;
//import com.example.demo.dto.UserTicketDetailsResponseList;
//import com.example.demo.entity.UserTicketDetails;
//
///**
// * @author Rajkumar Banala 05-Mar-2021
// *
// */
//
//@Mapper
//public interface UserTicketDetailsMapper {
//	
//	UserTicketDetailsMapper INSTANCE = Mappers.getMapper(UserTicketDetailsMapper.class);
//	
//	public List<UserTicketDetails> toUserTicketDetails(List<UserTicketDetailsRequestCreate> listDTO);
//	
//	public UserTicketDetailsResponseCreate toUserTicketDetailsResponseCreate(UserTicketDetails entity);
//	
//	public List<UserTicketDetailsResponseList> toUserTicketDetailsResponseList(List<UserTicketDetails> entityList);
//
//}
