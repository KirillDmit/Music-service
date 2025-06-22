package org.example.repository;

import org.example.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByTrackId(UUID trackId);

    List<Review> findByUserId(UUID userId);

    List<Review> findByTrackIdAndIsHiddenFalse(UUID trackId);
}