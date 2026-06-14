package com.rentacostume.reviews.kafka;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class RentalCompletedEvent {

    private String eventId;
    private int eventVersion;
    private OffsetDateTime occurredAt;

    private String vendorOrderId;
    private String parentOrderId;

    private String vendorId;
    private String vendorName;

    private String userId;
    private String userEmail;
    private String userPhone;

    private String costumeId;
    private String costumeTitle;
    private Integer quantity;

    private String status;
}