package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Review;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final TrackService trackService;
    private final UserService userService;

    public Review create(Review review, UUID trackId, UUID userId) {
        review.setTrack(trackService.findById(trackId).orElseThrow(
                () -> new EntityNotFoundException("Track not found")));
        review.setUser(userService.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found")));
        return reviewRepository.save(review);
    }

    public List<Review> findByTrackId(UUID trackId) {
        return reviewRepository.findByTrackId(trackId);
    }

    public List<Review> findVisibleByTrackId(UUID trackId) {
        return reviewRepository.findByTrackIdAndIsHiddenFalse(trackId);
    }

    public Optional<Review> findById(UUID id) {
        return reviewRepository.findById(id);
    }

    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(UUID id) {
        reviewRepository.deleteById(id);
    }
}