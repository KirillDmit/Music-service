package org.example.dto;

import lombok.Data;

@Data
public class ReviewCreateDto {
    private Long trackId;
    private int rating;
    private String reviewText;
}
