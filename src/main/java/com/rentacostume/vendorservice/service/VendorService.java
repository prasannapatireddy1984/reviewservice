package com.rentacostume.vendorservice.service;

import com.rentacostume.vendorservice.model.Vendor;
import com.rentacostume.vendorservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor registerVendor(Vendor vendor) {
        vendor.setRegisteredAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }

    public Optional<Vendor> getVendorById(String id) {
        return vendorRepository.findById(id);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
