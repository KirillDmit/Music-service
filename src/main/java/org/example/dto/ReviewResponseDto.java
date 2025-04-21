package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {
    private Long id;
    private String username;
    private Long trackId;
    private int rating;
    private String reviewText;
}
