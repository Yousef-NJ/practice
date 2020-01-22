package com.training.adapter.repositories.cartrepository.mysqlcartrepoimp;

import com.training.adapter.repositories.cartrepository.CartRepository;
import com.training.adapter.repositories.cartrepository.mysqlcartrepoimp.entity.CartEntity;
import com.training.adapter.repositories.itemrepository.mysqlitemrepoimp.entity.ItemEntity;
import com.training.model.Cart;
import com.training.model.CartStatus;
import com.training.model.Item;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MysqlCartRepoImp implements CartRepository {
    private final MysqlCartRepoJPA repo;
    private final ModelMapper mapper;

    public MysqlCartRepoImp(MysqlCartRepoJPA repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Cart createCart(Cart cart) {
        repo.save(CartToCartEntity(cart));
        this.updateStatus(CartStatus.CREATED,CartToCartEntity(cart));
        return cart;
    }

// TODO : Check if the cart given by the parameters exist.

    @Override
    public void addToCart(String cartId , Item... items) {
        CartEntity cart = repo.getOne(cartId);
        List<ItemEntity> newItemList = cart.getItems();
        newItemList.stream().forEach(item -> ItemEntityToItem(item));
        cart.setItems(newItemList);
        this.updateTotalPrice(cartId);
        cart.setStatus(CartStatus.SAVED);
        repo.save(cart);
    }

    @Override
    public Cart clearCart(String cartId) {
        CartEntity cart=this.makeCartEmpty(cartId);
        this.updateStatus(CartStatus.CREATED,cart);
        return CartEntityToCart(repo.save(cart));
    }

    @Override
    public Cart checkout(String cartId) {
        CartEntity cart = repo.getOne(cartId);
        this.updateStatus(CartStatus.CHECKOUT,cart);
        this.makeItAvailableForPayment(cartId);
        return CartEntityToCart(repo.save(cart));
    }

    private void makeItAvailableForPayment(String cartId) {
        CartEntity cart = repo.getOne(cartId);
        updateTotalPrice(cartId);
        this.updateStatus(CartStatus.PENDING_PAYMENT,cart);
    }

    @Override
    public Cart cancelCart(String cartId) {
        CartEntity cart=this.makeCartEmpty(cartId);
        updateStatus(CartStatus.CANCELED,cart);
        return CartEntityToCart(repo.save(cart));
    }

    @Override
    public Optional<Cart> getCart(String cartId) {
        CartEntity cart = repo.getOne(cartId);
        return (cart == null) ? Optional.of(CartEntityToCart(cart)) : Optional.empty();
    }

    @Override
    public List<Cart> findAll() {
        return repo.findAll().stream().map(this::CartEntityToCart).collect(Collectors.toList());
    }

    private CartEntity makeCartEmpty(String cartId) {
        CartEntity cart = repo.getOne(cartId);
        List<ItemEntity> newEmptyItemList = cart.getItems();
        newEmptyItemList.clear();
        cart.setItems(newEmptyItemList);
        return cart;
    }

    private void updateStatus(CartStatus status,CartEntity cart) {
        cart.setStatus(status);
    }

    private void updateTotalPrice(String cartId) {
        CartEntity cart = repo.getOne(cartId);
        List<ItemEntity> items = cart.getItems();
        Integer summation = items.stream().map(itemEntity -> itemEntity.getPrice()).reduce(0, (sum, price) -> sum + price);
        cart.setTotalPrice(summation);
    }

    private Cart CartEntityToCart(CartEntity cartEntity) {
        return mapper.map(cartEntity, Cart.class);
    }

    private CartEntity CartToCartEntity(Cart cart) {
        return mapper.map(cart, CartEntity.class);
    }

    private Item ItemEntityToItem(ItemEntity itemEntity) {
        return mapper.map(itemEntity, Item.class);
    }

    private ItemEntity ItemToItemEntity(Item item) {
        return mapper.map(item, ItemEntity.class);
    }

}
