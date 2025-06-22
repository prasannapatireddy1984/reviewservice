package com.rentacostume.vendorservice.service;

import com.rentacostume.vendorservice.model.Offer;
import com.rentacostume.vendorservice.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public List<Offer> getOffersByVendor(String vendorId) {
        return offerRepository.findByVendorId(vendorId);
    }
}
