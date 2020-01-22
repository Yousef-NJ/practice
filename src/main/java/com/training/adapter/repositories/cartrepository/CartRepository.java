package com.training.adapter.repositories.cartrepository;

import com.training.model.Cart;
import com.training.model.Item;

import java.util.List;
import java.util.Optional;


public interface CartRepository {

    public Cart createCart(Cart cart);

    public void addToCart(String cartId, Item ...items);

    public Cart clearCart(String cartId);

    public Cart checkout(String cartId);

    public Cart cancelCart(String cartId);

    public Optional<Cart> getCart(String cartId);

    public List<Cart> findAll();
}
