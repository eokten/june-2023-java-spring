package com.okten.springdemo.dao;

import com.okten.springdemo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    private final EntityManager entityManager;

    public List<Product> getProducts() {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    @Transactional
    public Product saveProduct(Product product) {
        entityManager.persist(product);
        return product;
    }
}
