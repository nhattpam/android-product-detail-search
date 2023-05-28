package com.nhattpam.productdetailrecyler3;

import java.io.Serializable;

public class Product implements Serializable {

    private int imageId;
    private String name;
    private String price;
    private String quantity;
    private String description;

    public Product(int imageId, String name, String price, String quantity, String description) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
