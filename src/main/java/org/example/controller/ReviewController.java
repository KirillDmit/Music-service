package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Review;
import org.example.service.ReviewService;
import org.example.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final TrackService trackService;

    @GetMapping("/track/{trackId}")
    public ResponseEntity<List<Review>> getReviewsByTrack(@PathVariable Long trackId) {
        return trackService.getById(trackId)
                .map(track -> ResponseEntity.ok(reviewService.getReviewsByTrack(track)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        // Здесь можно добавить логику по проверке прав пользователя
        return ResponseEntity.ok(reviewService.save(review));
    }
}
