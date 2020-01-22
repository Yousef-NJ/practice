package com.training.service;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.domain.Page;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.training.adapter.messages.outbound.ItemEventChannel;
import com.training.adapter.repositories.itemrepository.ItemRepository;
import com.training.events.ItemAddedEvent;
import com.training.model.Item;

@Service
@EnableBinding(ItemEventChannel.class)
public class ItemService {
	private final ItemRepository repo;
	private final ItemEventChannel itemEventChannel;

	public ItemService(ItemRepository repo,ItemEventChannel itemEventChannel) {
		this.repo = repo;
		this.itemEventChannel = itemEventChannel;
	}
	
    public Item addItem(Item item) {
    	Item addedItem = repo.addItem(item);
    	ItemAddedEvent itemAddedEvent = ItemAddedEvent
    			.builder()
    			.description(addedItem.getDescription())
    			.images(addedItem.getImages())
    			.itemNo(addedItem.getItemNo())
    			.price(addedItem.getPrice())
    			.quantity(addedItem.getQuantity())
    			.manId(addedItem.getManId())
    			.name(addedItem.getName())
    			.build();
    	Message<ItemAddedEvent> msg=MessageBuilder
    			.withPayload(itemAddedEvent)
    			.build();
    	itemEventChannel.outputEvents().send(msg);
    	return addedItem;
    }
	
	public Page<Item> loadItems(int pageIndex,int pageSize){
		return repo.loadItems(pageIndex, pageSize);
	}
	
	public Optional<Item> loadItem(String itemNo){
		return repo.loadItem(itemNo);
	}

}
