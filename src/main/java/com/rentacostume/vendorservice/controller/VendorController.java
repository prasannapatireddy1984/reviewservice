package com.rentacostume.vendorservice.controller;

import com.rentacostume.vendorservice.model.Offer;
import com.rentacostume.vendorservice.model.Vendor;
import com.rentacostume.vendorservice.service.OfferService;
import com.rentacostume.vendorservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @Autowired
    private OfferService offerService;

    @PostMapping("/register")
    public Vendor registerVendor(@RequestBody Vendor vendor) {
        return vendorService.registerVendor(vendor);
    }

    @GetMapping("/{id}")
    public Optional<Vendor> getVendor(@PathVariable String id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PostMapping("/{vendorId}/offers")
    public Offer createOffer(@PathVariable String vendorId, @RequestBody Offer offer) {
        offer.setVendorId(vendorId);
        return offerService.createOffer(offer);
    }

    @GetMapping("/{vendorId}/offers")
    public List<Offer> getOffers(@PathVariable String vendorId) {
        return offerService.getOffersByVendor(vendorId);
    }
}