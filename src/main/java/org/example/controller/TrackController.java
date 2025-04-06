package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Track;
import org.example.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping("/search")
    public List<Track> search(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String artist,
                              @RequestParam(required = false) String genre) {
        if (Objects.nonNull(title))
            return trackService.searchByTitle(title);
        if (Objects.nonNull(artist))
            return trackService.searchByArtist(artist);
        if (Objects.nonNull(genre))
            return trackService.searchByGenre(genre);
        return List.of();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        return trackService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Track> addTrack(@RequestBody Track track) {
        return ResponseEntity.ok(trackService.saveTrack(track));
    }
}
