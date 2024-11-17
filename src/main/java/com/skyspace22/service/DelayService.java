package com.skyspace22.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace22.domain.Delay;
import com.skyspace22.dto.DelayDTO;
import com.skyspace22.dto.DelaySearchDTO;
import com.skyspace22.dto.DelayPageDTO;
import com.skyspace22.dto.DelayConvertCriteriaDTO;
import com.skyspace22.service.GenericService;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DelayService extends GenericService<Delay, Integer> {

	List<Delay> findAll();

	ResultDTO addDelay(DelayDTO delayDTO, RequestDTO requestDTO);

	ResultDTO updateDelay(DelayDTO delayDTO, RequestDTO requestDTO);

    Page<Delay> getAllDelays(Pageable pageable);

    Page<Delay> getAllDelays(Specification<Delay> spec, Pageable pageable);

	ResponseEntity<DelayPageDTO> getDelays(DelaySearchDTO delaySearchDTO);
	
	List<DelayDTO> convertDelaysToDelayDTOs(List<Delay> delays, DelayConvertCriteriaDTO convertCriteria);

	DelayDTO getDelayDTOById(Integer delayId);







}





