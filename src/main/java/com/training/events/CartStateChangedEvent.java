package com.training.events;

import com.training.model.CartStatus;
import com.training.model.Item;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CartStateChangedEvent {
    private String id;
    private CartStatus status;
    private List<Item> items ;
    private double totalPrice;
    @Builder.Default
    private String type = "CartStateChangedEvent";
}
