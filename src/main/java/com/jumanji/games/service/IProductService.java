package com.jumanji.games.service;

import com.jumanji.games.entity.Product;

public interface IProductService {
    Iterable<Product> getAll();
    Product getById(Long id);
    void insert(Product client);
    void update(Long id, Product client);
    void delete(Long id);
}
