package com.vanhieu.dto;

public class ItemDto extends AbstractDto<ItemDto>{

    private Integer price;
    private String name;
    private String image;
    private String categoryCode;
    private Integer categoryId;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                '}';
    }
}
