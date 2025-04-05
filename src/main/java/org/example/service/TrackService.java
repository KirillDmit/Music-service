package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Track;
import org.example.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }
}
