package com.system.view;

import com.system.exception.DuplicateReviewException;
import com.system.exception.ProductNotFoundException;
import com.system.exception.UserNotFoundException;
import com.system.model.Product;
import com.system.model.User;
import com.system.service.EvaluationService;
import com.system.service.ProductService;
import com.system.service.UserService;

import java.util.Scanner;

public class ConsoleInterface {
    private final UserService userService;
    private final ProductService productService;
    private final EvaluationService evaluationService;
    private final Scanner scanner;

    public ConsoleInterface(UserService userService, ProductService productService, EvaluationService evaluationService) {
        this.userService = userService;
        this.productService = productService;
        this.evaluationService = evaluationService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Product Evaluation System ---");
            System.out.println("1. Register User");
            System.out.println("2. Register Product");
            System.out.println("3. Evaluate Product");
            System.out.println("4. List All Users");
            System.out.println("5. List All Products");
            System.out.println("6. List All Reviews");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = -1;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine(); // clear buffer
                continue;
            }

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    registerProduct();
                    break;
                case 3:
                    evaluateProduct();
                    break;
                case 4:
                    listUsers();
                    break;
                case 5:
                    listProducts();
                    break;
                case 6:
                    listReviews();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        userService.createUser(name, email);
        System.out.println("User registered successfully.");
    }

    private void registerProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String desc = scanner.nextLine();
        productService.createProduct(name, desc);
        System.out.println("Product registered successfully.");
    }

    private void evaluateProduct() {
        try {
            System.out.print("Enter User ID: ");
            Long userId = scanner.nextLong();
            System.out.print("Enter Product ID: ");
            Long productId = scanner.nextLong();
            System.out.print("Enter Score (1-5): ");
            int score = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter Comment: ");
            String comment = scanner.nextLine();

            User user = userService.getUserById(userId);
            Product product = productService.getProductById(productId);

            evaluationService.evaluateProduct(user, product, score, comment);
            System.out.println("Product evaluated successfully.");
        } catch (UserNotFoundException | ProductNotFoundException | DuplicateReviewException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); // clear buffer
        }
    }

    private void listUsers() {
        System.out.println("\n--- Users ---");
        userService.getAllUsers().forEach(System.out::println);
    }

    private void listProducts() {
        System.out.println("\n--- Products ---");
        productService.getAllProducts().forEach(System.out::println);
    }

    private void listReviews() {
        System.out.println("\n--- Reviews ---");
        evaluationService.getAllReviews().forEach(System.out::println);
    }
}
