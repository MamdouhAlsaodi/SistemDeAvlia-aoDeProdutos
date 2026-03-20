package com.system.service;

import com.system.exception.DuplicateReviewException;
import com.system.model.Product;
import com.system.model.Review;
import com.system.model.User;
import com.system.repository.ReviewRepository;

import java.util.List;

public class EvaluationService {
    private final ReviewRepository reviewRepository;

    public EvaluationService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void evaluateProduct(User user, Product product, int score, String comment) throws DuplicateReviewException {
        if (reviewRepository.existsByUserAndProduct(user.getId(), product.getId())) {
            throw new DuplicateReviewException("Error: User has already evaluated this product.");
        }
        Review review = new Review(null, user, product, score, comment);
        reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
