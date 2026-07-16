package org.example.productcatalog.service;

import org.example.productcatalog.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
