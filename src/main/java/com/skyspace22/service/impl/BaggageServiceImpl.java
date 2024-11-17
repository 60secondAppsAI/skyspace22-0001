package com.skyspace22.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace22.dao.GenericDAO;
import com.skyspace22.service.GenericService;
import com.skyspace22.service.impl.GenericServiceImpl;
import com.skyspace22.dao.BaggageDAO;
import com.skyspace22.domain.Baggage;
import com.skyspace22.dto.BaggageDTO;
import com.skyspace22.dto.BaggageSearchDTO;
import com.skyspace22.dto.BaggagePageDTO;
import com.skyspace22.dto.BaggageConvertCriteriaDTO;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import com.skyspace22.service.BaggageService;
import com.skyspace22.util.ControllerUtils;





@Service
public class BaggageServiceImpl extends GenericServiceImpl<Baggage, Integer> implements BaggageService {

    private final static Logger logger = LoggerFactory.getLogger(BaggageServiceImpl.class);

	@Autowired
	BaggageDAO baggageDao;

	


	@Override
	public GenericDAO<Baggage, Integer> getDAO() {
		return (GenericDAO<Baggage, Integer>) baggageDao;
	}
	
	public List<Baggage> findAll () {
		List<Baggage> baggages = baggageDao.findAll();
		
		return baggages;	
		
	}

	public ResultDTO addBaggage(BaggageDTO baggageDTO, RequestDTO requestDTO) {

		Baggage baggage = new Baggage();

		baggage.setBaggageId(baggageDTO.getBaggageId());


		baggage.setWeight(baggageDTO.getWeight());


		baggage.setBaggageType(baggageDTO.getBaggageType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		baggage = baggageDao.save(baggage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Baggage> getAllBaggages(Pageable pageable) {
		return baggageDao.findAll(pageable);
	}

	public Page<Baggage> getAllBaggages(Specification<Baggage> spec, Pageable pageable) {
		return baggageDao.findAll(spec, pageable);
	}

	public ResponseEntity<BaggagePageDTO> getBaggages(BaggageSearchDTO baggageSearchDTO) {
	
			Integer baggageId = baggageSearchDTO.getBaggageId(); 
  			String baggageType = baggageSearchDTO.getBaggageType(); 
 			String sortBy = baggageSearchDTO.getSortBy();
			String sortOrder = baggageSearchDTO.getSortOrder();
			String searchQuery = baggageSearchDTO.getSearchQuery();
			Integer page = baggageSearchDTO.getPage();
			Integer size = baggageSearchDTO.getSize();

	        Specification<Baggage> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, baggageId, "baggageId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, baggageType, "baggageType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("baggageType")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Baggage> baggages = this.getAllBaggages(spec, pageable);
		
		//System.out.println(String.valueOf(baggages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(baggages.getTotalPages()));
		
		List<Baggage> baggagesList = baggages.getContent();
		
		BaggageConvertCriteriaDTO convertCriteria = new BaggageConvertCriteriaDTO();
		List<BaggageDTO> baggageDTOs = this.convertBaggagesToBaggageDTOs(baggagesList,convertCriteria);
		
		BaggagePageDTO baggagePageDTO = new BaggagePageDTO();
		baggagePageDTO.setBaggages(baggageDTOs);
		baggagePageDTO.setTotalElements(baggages.getTotalElements());
		return ResponseEntity.ok(baggagePageDTO);
	}

	public List<BaggageDTO> convertBaggagesToBaggageDTOs(List<Baggage> baggages, BaggageConvertCriteriaDTO convertCriteria) {
		
		List<BaggageDTO> baggageDTOs = new ArrayList<BaggageDTO>();
		
		for (Baggage baggage : baggages) {
			baggageDTOs.add(convertBaggageToBaggageDTO(baggage,convertCriteria));
		}
		
		return baggageDTOs;

	}
	
	public BaggageDTO convertBaggageToBaggageDTO(Baggage baggage, BaggageConvertCriteriaDTO convertCriteria) {
		
		BaggageDTO baggageDTO = new BaggageDTO();
		
		baggageDTO.setBaggageId(baggage.getBaggageId());

	
		baggageDTO.setWeight(baggage.getWeight());

	
		baggageDTO.setBaggageType(baggage.getBaggageType());

	

		
		return baggageDTO;
	}

	public ResultDTO updateBaggage(BaggageDTO baggageDTO, RequestDTO requestDTO) {
		
		Baggage baggage = baggageDao.getById(baggageDTO.getBaggageId());

		baggage.setBaggageId(ControllerUtils.setValue(baggage.getBaggageId(), baggageDTO.getBaggageId()));

		baggage.setWeight(ControllerUtils.setValue(baggage.getWeight(), baggageDTO.getWeight()));

		baggage.setBaggageType(ControllerUtils.setValue(baggage.getBaggageType(), baggageDTO.getBaggageType()));



        baggage = baggageDao.save(baggage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BaggageDTO getBaggageDTOById(Integer baggageId) {
	
		Baggage baggage = baggageDao.getById(baggageId);
			
		
		BaggageConvertCriteriaDTO convertCriteria = new BaggageConvertCriteriaDTO();
		return(this.convertBaggageToBaggageDTO(baggage,convertCriteria));
	}







}
