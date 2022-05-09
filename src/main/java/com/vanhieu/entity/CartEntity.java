package com.vanhieu.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity{

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemCart;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ItemEntity getItemCart() {
        return itemCart;
    }

    public void setItemCart(ItemEntity itemCart) {
        this.itemCart = itemCart;
    }

}
