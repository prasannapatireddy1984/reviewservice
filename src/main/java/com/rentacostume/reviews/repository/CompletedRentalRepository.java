package com.rentacostume.reviews.repository;

import com.rentacostume.reviews.model.CompletedRental;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompletedRentalRepository extends MongoRepository<CompletedRental, String> {

    boolean existsByUserIdAndOrderIdAndProductId(
            String userId,
            String orderId,
            String productId
    );
}