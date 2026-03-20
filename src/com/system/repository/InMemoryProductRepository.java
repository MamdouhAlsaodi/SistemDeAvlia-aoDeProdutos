package com.system.repository;

import com.system.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private long currentId = 1;

    @Override
    public void save(Product entity) {
        if (entity.getId() == null) {
            entity.setId(currentId++);
            products.add(entity);
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(entity.getId())) {
                    products.set(i, entity);
                    return;
                }
            }
            products.add(entity);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
