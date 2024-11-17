package com.skyspace22.dao;

import java.util.List;

import com.skyspace22.dao.GenericDAO;
import com.skyspace22.domain.Member;





public interface MemberDAO extends GenericDAO<Member, Integer> {
  
	List<Member> findAll();
	






}


