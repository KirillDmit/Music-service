package org.example.controller;

import org.example.entity.Track;
import org.example.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    @PostMapping
    public ResponseEntity<Track> addTrack(@RequestBody Track track) {
        return ResponseEntity.ok(trackService.saveTrack(track));
    }
}
