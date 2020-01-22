package com.training.adapter.rest;

import com.training.adapter.rest.dto.CartDTO;
import com.training.exception.CartNotFoundException;
import com.training.model.Cart;
import com.training.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    public final CartService service;
    public final ModelMapper mapper;

    public CartController(CartService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Cart> getAllCarts(){
        return service.getAllCarts();
    }

    @GetMapping("/{id}")
    public CartDTO getItem(@PathVariable("id") String id) {
        return service.getCart(id).map(this::CartToCartDTO).orElseThrow(() -> new CartNotFoundException(id));
    }

    @PostMapping
    public Cart createCart(@Valid @RequestBody CartDTO cartDTO){
        return service.createCart(CartDTOToCart(cartDTO));
    }

    @PatchMapping("/{id}")
    public CartDTO clearCart(@PathVariable("id") String id){
         return CartToCartDTO(service.clearCart(id));
    }

    @PatchMapping("/{id}")
    public CartDTO checkout(@PathVariable("id") String id){
        return CartToCartDTO(service.checkout(id));
    }

    @PatchMapping("/{id}")
    public CartDTO cancel(@PathVariable("id") String id){
        return CartToCartDTO(service.cancelCart(id));
    }

    private Cart CartDTOToCart(CartDTO cartDTO) {
        return mapper.map(cartDTO, Cart.class);
    }

    private CartDTO CartToCartDTO(Cart cart) {
        return mapper.map(cart, CartDTO.class);
    }
}
