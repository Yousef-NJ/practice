package com.training.adapter.rest;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.adapter.rest.dto.ItemDTO;
import com.training.exception.ItemNotFoundException;
import com.training.model.Item;
import com.training.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	private final ItemService itemService;
	private final ModelMapper modelMapper; 
	
	public ItemController(ItemService itemService, ModelMapper modelMapper) {
		this.itemService = itemService;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	public ItemDTO addItem(@Valid @RequestBody ItemDTO itemDTO) {
		Item item = ItemDTOToItem(itemDTO);
		itemService.addItem(item);
		return itemDTO;
	}
	
	@GetMapping("/{id}")
	public ItemDTO getItem(@PathVariable("id") String id) {
		return itemService.loadItem(id).map(this::ItemToItemDTO).orElseThrow(() -> new ItemNotFoundException(id));
	}
	
	@GetMapping
	public Page<ItemDTO> getAllItems(
			@RequestParam(name = "PageIndex",required = false,defaultValue = "0") int pageIndex,
			@RequestParam(name = "PageSize",required = false,defaultValue = "10") int pageSize)
	{
		return itemService.loadItems(pageIndex, pageSize).map(this::ItemToItemDTO);
	}
	
	private Item ItemDTOToItem(ItemDTO itemDTO) {
		return modelMapper.map(itemDTO,Item.class);
	}
	
	private ItemDTO ItemToItemDTO(Item item) {
		return modelMapper.map(item,ItemDTO.class);
	}

}
