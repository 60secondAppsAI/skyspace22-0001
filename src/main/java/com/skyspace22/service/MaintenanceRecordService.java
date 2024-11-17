package com.skyspace22.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace22.domain.MaintenanceRecord;
import com.skyspace22.dto.MaintenanceRecordDTO;
import com.skyspace22.dto.MaintenanceRecordSearchDTO;
import com.skyspace22.dto.MaintenanceRecordPageDTO;
import com.skyspace22.dto.MaintenanceRecordConvertCriteriaDTO;
import com.skyspace22.service.GenericService;
import com.skyspace22.dto.common.RequestDTO;
import com.skyspace22.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceRecordService extends GenericService<MaintenanceRecord, Integer> {

	List<MaintenanceRecord> findAll();

	ResultDTO addMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO);

    Page<MaintenanceRecord> getAllMaintenanceRecords(Pageable pageable);

    Page<MaintenanceRecord> getAllMaintenanceRecords(Specification<MaintenanceRecord> spec, Pageable pageable);

	ResponseEntity<MaintenanceRecordPageDTO> getMaintenanceRecords(MaintenanceRecordSearchDTO maintenanceRecordSearchDTO);
	
	List<MaintenanceRecordDTO> convertMaintenanceRecordsToMaintenanceRecordDTOs(List<MaintenanceRecord> maintenanceRecords, MaintenanceRecordConvertCriteriaDTO convertCriteria);

	MaintenanceRecordDTO getMaintenanceRecordDTOById(Integer maintenanceRecordId);







}





