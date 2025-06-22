package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Review;
import org.example.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/track/{trackId}")
    public ResponseEntity<List<Review>> getReviewsByTrack(@PathVariable UUID trackId) {
        return ResponseEntity.ok(reviewService.findByTrackId(trackId));
    }

    @GetMapping("/track/{trackId}/visible")
    public ResponseEntity<List<Review>> getVisibleReviewsByTrack(@PathVariable UUID trackId) {
        return ResponseEntity.ok(reviewService.findVisibleByTrackId(trackId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable UUID id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/track/{trackId}/user/{userId}")
    public ResponseEntity<Review> createReview(
            @PathVariable UUID trackId,
            @PathVariable UUID userId,
            @RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.create(review, trackId, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable UUID id, @RequestBody Review review) {
        review.setId(id);
        return ResponseEntity.ok(reviewService.update(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}