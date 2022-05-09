package com.vanhieu.dto;

public class CartDto extends AbstractDto<CartDto>{

    private int quantity;
    private int total;
    private String username;
    private Long userId;
    private Long itemId;
    private ItemDto itemDto;
    private int status;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "quantity=" + quantity +
                ", total=" + total +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", status=" + status +
                '}';
    }
}
