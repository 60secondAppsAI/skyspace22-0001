package com.skyspace22.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace22.domain.Member;
import com.skyspace22.dto.MemberDTO;
import com.skyspace22.dto.MemberSearchDTO;
import com.skyspace22.dto.MemberPageDTO;
import com.skyspace22.dto.MemberConvertCriteriaDTO;
import com.skyspace22.service.GenericService;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MemberService extends GenericService<Member, Integer> {

	List<Member> findAll();

	ResultDTO addMember(MemberDTO memberDTO, RequestDTO requestDTO);

	ResultDTO updateMember(MemberDTO memberDTO, RequestDTO requestDTO);

    Page<Member> getAllMembers(Pageable pageable);

    Page<Member> getAllMembers(Specification<Member> spec, Pageable pageable);

	ResponseEntity<MemberPageDTO> getMembers(MemberSearchDTO memberSearchDTO);
	
	List<MemberDTO> convertMembersToMemberDTOs(List<Member> members, MemberConvertCriteriaDTO convertCriteria);

	MemberDTO getMemberDTOById(Integer memberId);







}





