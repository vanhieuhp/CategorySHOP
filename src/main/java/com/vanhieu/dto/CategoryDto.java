package com.vanhieu.dto;

public class CategoryDto extends AbstractDto<CategoryDto> {

    private String name;
    private String code;
    private String image;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
/*
 * name VARCHAR(255) NOT NULL, code VARCHAR(255) NOT NULL,
 */
