package org.example.service;

import org.example.entity.Review;
import org.example.entity.Track;
import org.example.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReviewsByTrack_returnsReviews() {
        Track track = Track.builder().id(1L).title("Test").build();
        Review review = Review.builder().id(1L).track(track).rating(5).reviewText("Great!").build();
        when(reviewRepository.findByTrack(track)).thenReturn(List.of(review));

        List<Review> result = reviewService.getReviewsByTrack(track);

        assertEquals(1, result.size());
        assertEquals("Great!", result.get(0).getReviewText());
    }

    @Test
    void save_returnsSavedReview() {
        Review review = Review.builder().id(1L).rating(4).reviewText("Nice").build();
        when(reviewRepository.save(review)).thenReturn(review);

        Review saved = reviewService.save(review);

        assertEquals("Nice", saved.getReviewText());
    }
}
