package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.FlightSchedule;





public interface FlightScheduleDAO extends GenericDAO<FlightSchedule, Integer> {
  
	List<FlightSchedule> findAll();
	






}


