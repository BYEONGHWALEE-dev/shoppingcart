package com.shoppingcar.forMajo.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "image_URL")
    private String image_URL;

    //constructor

    public Product() {}

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // getter / setter method

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }
}
