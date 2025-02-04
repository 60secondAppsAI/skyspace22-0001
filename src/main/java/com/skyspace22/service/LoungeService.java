package com.skyspace22.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace22.domain.Lounge;
import com.skyspace22.dto.LoungeDTO;
import com.skyspace22.dto.LoungeSearchDTO;
import com.skyspace22.dto.LoungePageDTO;
import com.skyspace22.dto.LoungeConvertCriteriaDTO;
import com.skyspace22.service.GenericService;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungeService extends GenericService<Lounge, Integer> {

	List<Lounge> findAll();

	ResultDTO addLounge(LoungeDTO loungeDTO, RequestDTO requestDTO);

	ResultDTO updateLounge(LoungeDTO loungeDTO, RequestDTO requestDTO);

    Page<Lounge> getAllLounges(Pageable pageable);

    Page<Lounge> getAllLounges(Specification<Lounge> spec, Pageable pageable);

	ResponseEntity<LoungePageDTO> getLounges(LoungeSearchDTO loungeSearchDTO);
	
	List<LoungeDTO> convertLoungesToLoungeDTOs(List<Lounge> lounges, LoungeConvertCriteriaDTO convertCriteria);

	LoungeDTO getLoungeDTOById(Integer loungeId);







}





