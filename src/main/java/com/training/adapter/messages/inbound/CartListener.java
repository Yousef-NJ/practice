package com.training.adapter.messages.inbound;

import com.training.model.Cart;
import com.training.service.CartService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(CartInputChannel.class)
public class CartListener {
    private CartService cartService;

    @StreamListener(CartInputChannel.INPUT)
    public void handleMessage(Cart cart){
        cartService.createCart(cart);
    }
}
