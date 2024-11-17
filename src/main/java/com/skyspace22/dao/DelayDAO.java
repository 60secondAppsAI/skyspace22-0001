package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.Delay;





public interface DelayDAO extends GenericDAO<Delay, Integer> {
  
	List<Delay> findAll();
	






}


