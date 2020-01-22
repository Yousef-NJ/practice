package com.training.service;

import com.training.adapter.repositories.cartrepository.CartRepository;
import com.training.adapter.repositories.cartrepository.mysqlcartrepoimp.entity.CartEntity;
import com.training.adapter.repositories.itemrepository.mysqlitemrepoimp.entity.ItemEntity;
import com.training.model.Cart;
import com.training.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    public final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Cart cart){
        return repo.createCart(cart);
    }

    public void addToCart(String cartId , Item... items) {
       repo.addToCart(cartId,items);
    }

    public Cart clearCart(String cartId) {
        return repo.clearCart(cartId);
    }

    public Cart checkout(String cartId) {
        return repo.checkout(cartId);
    }

    public Cart cancelCart(String cartId) {
        return repo.cancelCart(cartId);
    }

    public Optional<Cart> getCart(String cartId){
        return repo.getCart(cartId);
    }

    public List<Cart> getAllCarts() {
        return repo.findAll();
    }
}
