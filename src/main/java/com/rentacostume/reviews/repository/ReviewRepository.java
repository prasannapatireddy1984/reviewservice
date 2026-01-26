package com.rentacostume.reviews.repository;

import com.rentacostume.reviews.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Page<Review> findByProductId(String productId, Pageable pageable);
    Optional<Review> findByUserIdAndProductId(String userId, String productId);
}
