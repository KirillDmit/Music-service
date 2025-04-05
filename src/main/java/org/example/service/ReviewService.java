package org.example.service;

import org.example.entity.Review;
import org.example.entity.Track;
import org.example.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByTrack(Track track) {
        return reviewRepository.findByTrack(track);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
