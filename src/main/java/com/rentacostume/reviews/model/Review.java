package com.rentacostume.reviews.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("reviews")
@CompoundIndex(name = "user_product_unique", def = "{'userId': 1, 'productId': 1}", unique = true)
public class Review {
    @Id private String id;
    private String productId;
    private String userId;
    private double rating;              // 1..5, allow .5
    private String title;
    private String body;
    private List<String> images;
    private boolean verifiedPurchase;
    private int helpfulCount;
    private int notHelpfulCount;
    private boolean reported;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
