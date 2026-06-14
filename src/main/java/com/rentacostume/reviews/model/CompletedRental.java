package com.rentacostume.reviews.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@Document("completed_rentals")
@CompoundIndex(
        name = "completed_rental_unique",
        def = "{'userId': 1, 'orderId': 1, 'productId': 1}",
        unique = true
)
public class CompletedRental {

    @Id
    private String id;

    private String userId;
    private String orderId;
    private String productId;

    private String costumeTitle;
    private String vendorId;
    private String vendorName;

    private OffsetDateTime completedAt;
}