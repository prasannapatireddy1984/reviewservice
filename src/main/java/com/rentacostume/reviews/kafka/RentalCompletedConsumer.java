package com.rentacostume.reviews.kafka;

import com.rentacostume.reviews.model.CompletedRental;
import com.rentacostume.reviews.repository.CompletedRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalCompletedConsumer {

    private final CompletedRentalRepository repository;

    @KafkaListener(
            topics = "${rentacostume.kafka.topics.vendorItemStatusChanged}",
            groupId = "reviews-service"
    )
    public void consume(RentalCompletedEvent event) {

        if (!"COMPLETED".equalsIgnoreCase(event.getStatus())) {
            return;
        }

        boolean exists = repository.existsByUserIdAndOrderIdAndProductId(
                event.getUserId(),
                event.getParentOrderId(),
                event.getCostumeId()
        );

        if (exists) return;

        CompletedRental rental = new CompletedRental();
        rental.setUserId(event.getUserId());
        rental.setOrderId(event.getParentOrderId());
        rental.setProductId(event.getCostumeId());
        rental.setCostumeTitle(event.getCostumeTitle());
        rental.setVendorId(event.getVendorId());
        rental.setVendorName(event.getVendorName());
        rental.setCompletedAt(event.getOccurredAt());

        repository.save(rental);
    }
}