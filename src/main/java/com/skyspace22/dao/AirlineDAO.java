package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.Airline;





public interface AirlineDAO extends GenericDAO<Airline, Integer> {
  
	List<Airline> findAll();
	






}


