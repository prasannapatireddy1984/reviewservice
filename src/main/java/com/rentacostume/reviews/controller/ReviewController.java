package com.rentacostume.reviews.controller;

import com.rentacostume.reviews.dto.CreateReviewRequest;
import com.rentacostume.reviews.model.Review;
import com.rentacostume.reviews.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<Review> create(@RequestHeader("X-User-Id") String userId,
                                         @Valid @RequestBody CreateReviewRequest req) {
        return ResponseEntity.ok(service.create(userId, req));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<Review>> list(@PathVariable String productId,
                                             @RequestParam(defaultValue = "recent") String sort,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        System.out.println("inside get mapping of reviews, value of productId>>>>"+productId);
        return ResponseEntity.ok(service.list(productId, sort, page, size));
    }

    @PostMapping("/{reviewId}/helpful")
    public ResponseEntity<Review> voteHelpful(@PathVariable String reviewId,
                                              @RequestParam boolean helpful) {
        return ResponseEntity.ok(service.voteHelpful(reviewId, helpful));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-User-Id") String userId,
                                       @PathVariable String reviewId) {
        service.delete(userId, reviewId);
        return ResponseEntity.noContent().build();
    }
}
