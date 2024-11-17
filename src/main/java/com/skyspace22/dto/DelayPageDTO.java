package com.skyspace22.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DelayPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<DelayDTO> delays;
}





