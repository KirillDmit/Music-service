package org.example.repository;

import org.example.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackRepository extends JpaRepository<Track, UUID> {
    List<Track> findByTitleContainingIgnoreCase(String title);

    List<Track> findByArtistContainingIgnoreCase(String artist);

    List<Track> findByGenre(String genre);
}
