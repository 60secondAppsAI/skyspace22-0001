package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


