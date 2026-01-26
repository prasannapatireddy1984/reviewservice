package com.rentacostume.reviews.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    private String id;
    private String productId;
    private String userId;
    private double rating;
    private String title;
    private String body;
    private List<String> images;
    private boolean verifiedPurchase;
    private int helpfulCount;
    private int notHelpfulCount;
    private boolean reported;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- Constructors ---
    public ReviewResponse() {}

    public ReviewResponse(String id, String productId, String userId, double rating,
                          String title, String body, List<String> images,
                          boolean verifiedPurchase, int helpfulCount, int notHelpfulCount,
                          boolean reported, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.title = title;
        this.body = body;
        this.images = images;
        this.verifiedPurchase = verifiedPurchase;
        this.helpfulCount = helpfulCount;
        this.notHelpfulCount = notHelpfulCount;
        this.reported = reported;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getters and Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isVerifiedPurchase() {
        return verifiedPurchase;
    }

    public void setVerifiedPurchase(boolean verifiedPurchase) {
        this.verifiedPurchase = verifiedPurchase;
    }

    public int getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(int helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public int getNotHelpfulCount() {
        return notHelpfulCount;
    }

    public void setNotHelpfulCount(int notHelpfulCount) {
        this.notHelpfulCount = notHelpfulCount;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
