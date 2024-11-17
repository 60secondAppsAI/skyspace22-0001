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
import com.skyspace22.dao.MaintenanceRecordDAO;
import com.skyspace22.domain.MaintenanceRecord;
import com.skyspace22.dto.MaintenanceRecordDTO;
import com.skyspace22.dto.MaintenanceRecordSearchDTO;
import com.skyspace22.dto.MaintenanceRecordPageDTO;
import com.skyspace22.dto.MaintenanceRecordConvertCriteriaDTO;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import com.skyspace22.service.MaintenanceRecordService;
import com.skyspace22.util.ControllerUtils;





@Service
public class MaintenanceRecordServiceImpl extends GenericServiceImpl<MaintenanceRecord, Integer> implements MaintenanceRecordService {

    private final static Logger logger = LoggerFactory.getLogger(MaintenanceRecordServiceImpl.class);

	@Autowired
	MaintenanceRecordDAO maintenanceRecordDao;

	


	@Override
	public GenericDAO<MaintenanceRecord, Integer> getDAO() {
		return (GenericDAO<MaintenanceRecord, Integer>) maintenanceRecordDao;
	}
	
	public List<MaintenanceRecord> findAll () {
		List<MaintenanceRecord> maintenanceRecords = maintenanceRecordDao.findAll();
		
		return maintenanceRecords;	
		
	}

	public ResultDTO addMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO) {

		MaintenanceRecord maintenanceRecord = new MaintenanceRecord();

		maintenanceRecord.setMaintenanceRecordId(maintenanceRecordDTO.getMaintenanceRecordId());


		maintenanceRecord.setMaintenanceDate(maintenanceRecordDTO.getMaintenanceDate());


		maintenanceRecord.setDescription(maintenanceRecordDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		maintenanceRecord = maintenanceRecordDao.save(maintenanceRecord);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MaintenanceRecord> getAllMaintenanceRecords(Pageable pageable) {
		return maintenanceRecordDao.findAll(pageable);
	}

	public Page<MaintenanceRecord> getAllMaintenanceRecords(Specification<MaintenanceRecord> spec, Pageable pageable) {
		return maintenanceRecordDao.findAll(spec, pageable);
	}

	public ResponseEntity<MaintenanceRecordPageDTO> getMaintenanceRecords(MaintenanceRecordSearchDTO maintenanceRecordSearchDTO) {
	
			Integer maintenanceRecordId = maintenanceRecordSearchDTO.getMaintenanceRecordId(); 
   			String description = maintenanceRecordSearchDTO.getDescription(); 
 			String sortBy = maintenanceRecordSearchDTO.getSortBy();
			String sortOrder = maintenanceRecordSearchDTO.getSortOrder();
			String searchQuery = maintenanceRecordSearchDTO.getSearchQuery();
			Integer page = maintenanceRecordSearchDTO.getPage();
			Integer size = maintenanceRecordSearchDTO.getSize();

	        Specification<MaintenanceRecord> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, maintenanceRecordId, "maintenanceRecordId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MaintenanceRecord> maintenanceRecords = this.getAllMaintenanceRecords(spec, pageable);
		
		//System.out.println(String.valueOf(maintenanceRecords.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(maintenanceRecords.getTotalPages()));
		
		List<MaintenanceRecord> maintenanceRecordsList = maintenanceRecords.getContent();
		
		MaintenanceRecordConvertCriteriaDTO convertCriteria = new MaintenanceRecordConvertCriteriaDTO();
		List<MaintenanceRecordDTO> maintenanceRecordDTOs = this.convertMaintenanceRecordsToMaintenanceRecordDTOs(maintenanceRecordsList,convertCriteria);
		
		MaintenanceRecordPageDTO maintenanceRecordPageDTO = new MaintenanceRecordPageDTO();
		maintenanceRecordPageDTO.setMaintenanceRecords(maintenanceRecordDTOs);
		maintenanceRecordPageDTO.setTotalElements(maintenanceRecords.getTotalElements());
		return ResponseEntity.ok(maintenanceRecordPageDTO);
	}

	public List<MaintenanceRecordDTO> convertMaintenanceRecordsToMaintenanceRecordDTOs(List<MaintenanceRecord> maintenanceRecords, MaintenanceRecordConvertCriteriaDTO convertCriteria) {
		
		List<MaintenanceRecordDTO> maintenanceRecordDTOs = new ArrayList<MaintenanceRecordDTO>();
		
		for (MaintenanceRecord maintenanceRecord : maintenanceRecords) {
			maintenanceRecordDTOs.add(convertMaintenanceRecordToMaintenanceRecordDTO(maintenanceRecord,convertCriteria));
		}
		
		return maintenanceRecordDTOs;

	}
	
	public MaintenanceRecordDTO convertMaintenanceRecordToMaintenanceRecordDTO(MaintenanceRecord maintenanceRecord, MaintenanceRecordConvertCriteriaDTO convertCriteria) {
		
		MaintenanceRecordDTO maintenanceRecordDTO = new MaintenanceRecordDTO();
		
		maintenanceRecordDTO.setMaintenanceRecordId(maintenanceRecord.getMaintenanceRecordId());

	
		maintenanceRecordDTO.setMaintenanceDate(maintenanceRecord.getMaintenanceDate());

	
		maintenanceRecordDTO.setDescription(maintenanceRecord.getDescription());

	

		
		return maintenanceRecordDTO;
	}

	public ResultDTO updateMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO) {
		
		MaintenanceRecord maintenanceRecord = maintenanceRecordDao.getById(maintenanceRecordDTO.getMaintenanceRecordId());

		maintenanceRecord.setMaintenanceRecordId(ControllerUtils.setValue(maintenanceRecord.getMaintenanceRecordId(), maintenanceRecordDTO.getMaintenanceRecordId()));

		maintenanceRecord.setMaintenanceDate(ControllerUtils.setValue(maintenanceRecord.getMaintenanceDate(), maintenanceRecordDTO.getMaintenanceDate()));

		maintenanceRecord.setDescription(ControllerUtils.setValue(maintenanceRecord.getDescription(), maintenanceRecordDTO.getDescription()));



        maintenanceRecord = maintenanceRecordDao.save(maintenanceRecord);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MaintenanceRecordDTO getMaintenanceRecordDTOById(Integer maintenanceRecordId) {
	
		MaintenanceRecord maintenanceRecord = maintenanceRecordDao.getById(maintenanceRecordId);
			
		
		MaintenanceRecordConvertCriteriaDTO convertCriteria = new MaintenanceRecordConvertCriteriaDTO();
		return(this.convertMaintenanceRecordToMaintenanceRecordDTO(maintenanceRecord,convertCriteria));
	}







}
