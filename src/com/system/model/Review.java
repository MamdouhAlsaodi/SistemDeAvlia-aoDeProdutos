package com.system.model;

public class Review extends BaseEntity {
    private User user;
    private Product product;
    private int score;
    private String comment;

    public Review() {}

    public Review(Long id, User user, Product product, int score, String comment) {
        super(id);
        this.user = user;
        this.product = product;
        this.score = score;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + getId() +
                ", user=" + (user != null ? user.getName() : "null") +
                ", product=" + (product != null ? product.getName() : "null") +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
