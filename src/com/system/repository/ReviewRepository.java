package com.system.repository;

import com.system.model.Review;

public interface ReviewRepository extends IRepository<Review> {
    boolean existsByUserAndProduct(Long userId, Long productId);
}
