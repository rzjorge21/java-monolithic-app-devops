package com.mitocode.monolito.service;

import com.mitocode.monolito.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductService {
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1L, "Laptop", 1200.0));
        list.add(new Product(2L, "Mouse", 20.0));
        return list;
    }

    public Product getProductById(Long id) {
        return new Product(id, "Mock Product", 99.0);
    }
}
