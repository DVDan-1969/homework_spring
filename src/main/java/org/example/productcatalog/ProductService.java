package org.example.productcatalog;

import jakarta.annotation.PostConstruct;
import org.example.productcatalog.model.Product;
import org.example.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ProductService.class, args);
    }
    @PostConstruct
    public void init() {
        repository.save(new Product(null,"Laptop","Electronics",4500,8,true));
        repository.save(new Product(null,"Mouse","Electronics",120,25,true));
        repository.save(new Product(null,"Chair","Furniture",350,10,false));
    }

}
