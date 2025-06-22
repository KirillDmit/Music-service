package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Track;
import org.example.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public Track create(Track track) {
        return trackRepository.save(track);
    }

    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    public Optional<Track> findById(UUID id) {
        return trackRepository.findById(id);
    }

    public Track update(Track track) {
        return trackRepository.save(track);
    }

    public void delete(UUID id) {
        trackRepository.deleteById(id);
    }

    public List<Track> searchByTitle(String title) {
        return trackRepository.findByTitleContainingIgnoreCase(title);
    }
}
