package org.example.mapper;

import org.example.dto.ReviewResponseDto;
import org.example.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .username(review.getUser().getUsername())
                .trackId(review.getTrack().getId())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .build();
    }
}
