package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.MaintenanceRecord;





public interface MaintenanceRecordDAO extends GenericDAO<MaintenanceRecord, Integer> {
  
	List<MaintenanceRecord> findAll();
	






}


