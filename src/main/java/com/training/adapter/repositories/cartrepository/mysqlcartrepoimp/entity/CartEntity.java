package com.training.adapter.repositories.cartrepository.mysqlcartrepoimp.entity;

import com.training.adapter.repositories.itemrepository.mysqlitemrepoimp.entity.ItemEntity;
import com.training.model.CartStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
public class CartEntity {
    @Id
    private String id;
    private CartStatus status;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "item_cart")
    private List<ItemEntity> items ;
    private double totalPrice;
}
