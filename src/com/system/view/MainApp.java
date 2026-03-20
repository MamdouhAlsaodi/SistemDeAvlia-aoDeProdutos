package com.system.view;

import com.system.repository.InMemoryProductRepository;
import com.system.repository.InMemoryReviewRepository;
import com.system.repository.InMemoryUserRepository;
import com.system.repository.ProductRepository;
import com.system.repository.ReviewRepository;
import com.system.repository.UserRepository;
import com.system.service.EvaluationService;
import com.system.service.ProductService;
import com.system.service.UserService;

public class MainApp {
    public static void main(String[] args) {
        // Initialize Repositories
        UserRepository userRepository = new InMemoryUserRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        ReviewRepository reviewRepository = new InMemoryReviewRepository();

        // Initialize Services
        UserService userService = new UserService(userRepository);
        ProductService productService = new ProductService(productRepository);
        EvaluationService evaluationService = new EvaluationService(reviewRepository);

        // Populate with some initial data for testing
        userService.createUser("Alice", "alice@example.com");
        userService.createUser("Bob", "bob@example.com");

        productService.createProduct("Laptop", "High-end gaming laptop");
        productService.createProduct("Smartphone", "Latest model smartphone");

        // Start Interface
        ConsoleInterface consoleInterface = new ConsoleInterface(userService, productService, evaluationService);
        consoleInterface.start();
    }
}
