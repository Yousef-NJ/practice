package com.training.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Cart {
    private String id;
    private CartStatus status;
    private List<Item> items ;
    private double totalPrice;
}
