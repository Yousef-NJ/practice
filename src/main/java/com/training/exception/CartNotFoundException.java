package com.training.exception;

public class CartNotFoundException  extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Cart "+id+" not found");
    }
}
