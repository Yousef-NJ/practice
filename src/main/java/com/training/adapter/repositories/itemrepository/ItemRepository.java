package com.training.adapter.repositories.itemrepository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.training.model.Item;

public interface ItemRepository {
	
	public Item addItem(Item item);
	
	public Page<Item> loadItems(int pageIndex,int pageSize);
	
	public Optional<Item> loadItem(String itemNo);

}
