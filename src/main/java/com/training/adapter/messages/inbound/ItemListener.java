package com.training.adapter.messages.inbound;

import com.training.model.Item;
import com.training.service.ItemService;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(ItemInputChannel.class)
public class ItemListener {

    private ItemService itemService;

    @StreamListener(ItemInputChannel.INPUT)
    public void handleMessage(Item item){
        itemService.addItem(item);
    }
}