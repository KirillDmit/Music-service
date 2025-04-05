package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.entity.Review;
import org.example.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTrack(Track track);
}