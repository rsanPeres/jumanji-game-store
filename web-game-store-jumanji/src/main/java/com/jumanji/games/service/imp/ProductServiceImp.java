package com.jumanji.games.service.imp;

import com.jumanji.games.entity.Product;
import com.jumanji.games.repository.IProductRepository;
import com.jumanji.games.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    private IProductRepository repository;
    @Override
    public Iterable<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElse(null);
    }

    @Override
    public void insert(Product product) {
        repository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Optional<Product> prod = repository.findById(id);
        if(prod.isPresent()){
            prod.get().setId(id);
            repository.save(product);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = repository.findById(id);
        product.ifPresent(value -> repository.delete(value));
    }
}
