package org.example.controller;

import org.example.entity.Review;
import org.example.entity.Track;
import org.example.service.ReviewService;
import org.example.service.TrackService;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private TrackService trackService;

    @Test
    void getReviewsByTrack_whenTrackExists_returnsReviews() throws Exception {
        Track track = Track.builder().id(1L).title("Test Track").build();
        List<Review> reviews = List.of(
                Review.builder().id(1L).track(track).rating(5).reviewText("Awesome").build()
        );

        given(trackService.getById(1L)).willReturn(Optional.of(track));
        given(reviewService.getReviewsByTrack(track)).willReturn(reviews);

        mockMvc.perform(get("/api/reviews/track/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].reviewText").value("Awesome"));
    }

    @Test
    void getReviewsByTrack_whenTrackNotFound_returns404() throws Exception {
        given(trackService.getById(42L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/reviews/track/42"))
                .andExpect(status().isNotFound());
    }
}
