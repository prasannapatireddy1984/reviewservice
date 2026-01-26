package com.rentacostume.reviews.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReviewRequest {

        @NotBlank
        private String productId;

        @DecimalMin("1.0")
        @DecimalMax("5.0")
        private double rating;

        @Size(max = 120)
        private String title;

        @NotBlank
        private String body;

        private List<String> images;

        // --- Getters and Setters ---
        public String getProductId() {
                return productId;
        }

        public void setProductId(String productId) {
                this.productId = productId;
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
}
