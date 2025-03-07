package com.shoppingcar.forMajo.product.dao;

import com.shoppingcar.forMajo.product.entity.Cart;
import com.shoppingcar.forMajo.product.entity.Product;
import com.shoppingcar.forMajo.product.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

    // define EntityManager
    EntityManager entityManager;

    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // find method
    @Override
    public Product getProduct(int id) {
        Product theProduct = entityManager.find(Product.class, id);

        return theProduct;
    }

    @Override
    public Product getProduct(String productName) {
        TypedQuery<Product> theQuery = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
        theQuery.setParameter("name", productName);

        return theQuery.getSingleResult();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> theProducts = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();

        return theProducts;
    }

    // updateProdcut -> 사실 modify임
    @Override
    @Transactional
    public void modifyProductPrice(Product product, int price) {
        product.setPrice(price);
        entityManager.merge(product);
    }

    // add method
    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Cart addCart() {
        Cart theCart = new Cart();
        entityManager.persist(theCart);

        return theCart;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setCart(addCart());
        entityManager.persist(user);
    }

    // delete method
    @Override
    @Transactional
    public Product deleteProduct(Product product) {

        Product tempProduct = product;
        entityManager.remove(tempProduct);

        return tempProduct;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {

        entityManager.remove(entityManager.find(User.class, id));
    }


}
