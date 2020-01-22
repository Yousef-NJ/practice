package com.training.adapter.rest.dto;

import java.util.List;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ItemDTO {
	private String itemNo;
	private String name;
	private String description;
	@Min(0)
	private int manId;
	@Min(0)
	private int quantity;
	@Min(1)
	private int price;
	private List<ImageLinkDTO> images;

}
