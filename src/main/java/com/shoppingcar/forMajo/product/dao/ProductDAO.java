package com.shoppingcar.forMajo.product.dao;

import com.shoppingcar.forMajo.product.entity.Cart;
import com.shoppingcar.forMajo.product.entity.Product;
import com.shoppingcar.forMajo.product.entity.User;

import java.util.List;

public interface ProductDAO {

    // find method
    Product getProduct(int id);

    Product getProduct(String productName);

    List<Product> getAllProducts();

    // update method
    void modifyProductPrice(Product product, int price);

    // add method
    void addProduct(Product product);

    Cart addCart();

    void addUser(User user);

    // delete method
    Product deleteProduct(Product product);

    void deleteUser(int id);
}
