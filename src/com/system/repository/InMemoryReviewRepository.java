package com.system.repository;

import com.system.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryReviewRepository implements ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();
    private long currentId = 1;

    @Override
    public void save(Review entity) {
        if (entity.getId() == null) {
            entity.setId(currentId++);
            reviews.add(entity);
        } else {
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.get(i).getId().equals(entity.getId())) {
                    reviews.set(i, entity);
                    return;
                }
            }
            reviews.add(entity);
        }
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviews.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }

    @Override
    public void delete(Long id) {
        reviews.removeIf(r -> r.getId().equals(id));
    }

    @Override
    public boolean existsByUserAndProduct(Long userId, Long productId) {
        return reviews.stream()
                .anyMatch(r -> r.getUser().getId().equals(userId) && r.getProduct().getId().equals(productId));
    }
}
