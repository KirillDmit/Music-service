package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Track;
import org.example.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Optional<Track> getById(Long id) {
        return trackRepository.findById(id);
    }

    public List<Track> searchByTitle(String title) {
        return trackRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Track> searchByArtist(String artist) {
        return trackRepository.findByArtistContainingIgnoreCase(artist);
    }

    public List<Track> searchByGenre(String genre) {
        return trackRepository.findByGenre(genre);
    }

    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
}
