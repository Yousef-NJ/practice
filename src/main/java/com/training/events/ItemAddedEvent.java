package com.training.events;

import java.util.List;

import com.training.model.ImageLink;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ItemAddedEvent {

    private String itemNo;

    private String name;

    private int manId;

    private int quantity;

    private int price;

    private List<ImageLink> images;

    private String description;
    
    @Builder.Default
    private String type = "ItemAddedEvent";
}