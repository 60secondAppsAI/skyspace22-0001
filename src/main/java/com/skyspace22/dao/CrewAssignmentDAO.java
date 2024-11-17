package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.CrewAssignment;





public interface CrewAssignmentDAO extends GenericDAO<CrewAssignment, Integer> {
  
	List<CrewAssignment> findAll();
	






}


