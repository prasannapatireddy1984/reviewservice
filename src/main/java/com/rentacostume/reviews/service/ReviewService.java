package com.rentacostume.reviews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentacostume.reviews.dto.CreateReviewRequest;
import com.rentacostume.reviews.model.Review;
import com.rentacostume.reviews.repository.CompletedRentalRepository;
import com.rentacostume.reviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;
    private final KafkaTemplate<String, String> kafka;
    private final ObjectMapper objectMapper;
    private final CompletedRentalRepository completedRentalRepository;

    @Value("${rentacostume.kafka.topics.review.created}")
    private String topicCreated;
    @Value("${rentacostume.kafka.topics.review.updated}")
    private String topicUpdated;
    @Value("${rentacostume.kafka.topics.review.deleted}")
    private String topicDeleted;

    public Review create(String userId, CreateReviewRequest req) {
        repo.findByUserIdAndOrderIdAndProductId(
                userId,
                req.getOrderId(),
                req.getProductId()
        ).ifPresent(r -> {
            throw new IllegalStateException("You already reviewed this item for this reservation.");
        });

        boolean completedRental =
                completedRentalRepository.existsByUserIdAndOrderIdAndProductId(
                        userId,
                        req.getOrderId(),
                        req.getProductId()
                );

        if (!completedRental) {
            throw new IllegalStateException("Review allowed only for completed rentals.");
        }
        Review r = new Review();
        r.setUserId(userId);
        r.setOrderId(req.getOrderId());
        r.setProductId(req.getProductId());
        r.setRating(req.getRating());
        r.setTitle(req.getTitle());
        r.setBody(req.getBody());
        r.setImages(req.getImages() == null ? List.of() : req.getImages());
        r.setVerifiedPurchase(true);
        r.setHelpfulCount(0);
        r.setNotHelpfulCount(0);
        r.setReported(false);
        r.setCreatedAt(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());
        r = repo.save(r);
        System.out.println("Before Publishing"+topicCreated);
        publish(topicCreated, Map.of(
                "type","review.created",
                "reviewId", r.getId(),
                "orderId", r.getOrderId(),
                "productId", r.getProductId(),
                "userId", r.getUserId(),
                "rating", r.getRating(),
                "verifiedPurchase", r.isVerifiedPurchase()
        ));
        return r;
    }

    public Page<Review> list(String productId, String sort, int page, int size) {
        Sort s = switch (sort) {
            case "most_helpful" -> Sort.by(Sort.Direction.DESC, "helpfulCount").and(Sort.by(Sort.Direction.DESC, "createdAt"));
            case "highest_rating" -> Sort.by(Sort.Direction.DESC, "rating").and(Sort.by(Sort.Direction.DESC, "createdAt"));
            case "lowest_rating" -> Sort.by(Sort.Direction.ASC, "rating").and(Sort.by(Sort.Direction.DESC, "createdAt"));
            default -> Sort.by(Sort.Direction.DESC, "createdAt");
        };
        return repo.findByProductId(productId, PageRequest.of(page, size, s));
    }

    public Review voteHelpful(String reviewId, boolean helpful) {
        Review r = repo.findById(reviewId).orElseThrow();
        if (helpful) r.setHelpfulCount(r.getHelpfulCount() + 1);
        else r.setNotHelpfulCount(r.getNotHelpfulCount() + 1);
        r.setUpdatedAt(LocalDateTime.now());
        r = repo.save(r);
        publish(topicUpdated, Map.of(
                "type","review.updated",
                "reviewId", r.getId(),
                "productId", r.getProductId(),
                "helpfulCount", r.getHelpfulCount(),
                "notHelpfulCount", r.getNotHelpfulCount()
        ));
        return r;
    }

    public void delete(String userId, String reviewId) {
        Review r = repo.findById(reviewId).orElseThrow();
        if (!r.getUserId().equals(userId)) throw new SecurityException("Not your review.");
        repo.deleteById(reviewId);
        publish(topicDeleted, Map.of(
                "type","review.deleted",
                "reviewId", r.getId(),
                "productId", r.getProductId(),
                "rating", r.getRating()
        ));
    }

    private void publish(String topic, Map<String, Object> evt) {
        try {
            System.out.println("inside publish method");
            kafka.send(topic, (String) evt.getOrDefault("productId","*"), objectMapper.writeValueAsString(evt));
            System.out.println("After kafka send");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
