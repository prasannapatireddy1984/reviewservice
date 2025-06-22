package com.rentacostume.vendorservice.repository;

import com.rentacostume.vendorservice.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findByVendorId(String vendorId);
}