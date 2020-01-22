package com.training.adapter.rest.dto;

import com.training.model.CartStatus;
import com.training.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private String id;
    private CartStatus status;
    private List<Item> items ;
    private double totalPrice;
}
