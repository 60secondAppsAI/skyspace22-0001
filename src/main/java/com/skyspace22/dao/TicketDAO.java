package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.Ticket;





public interface TicketDAO extends GenericDAO<Ticket, Integer> {
  
	List<Ticket> findAll();
	






}


