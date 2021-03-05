package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Station;

/**
 * @author Rajkumar Banala 02-Mar-2021
 *
 */

@Repository
public interface StationDAO extends JpaRepository<Station, String> {

}
