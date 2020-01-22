package com.training.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Item {
	private String itemNo;
	private String name;
	private String description;
	private int manId;
	private int quantity;
	private int price;
	private List<ImageLink> images;

}
