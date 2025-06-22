package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Playlist;
import org.example.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Playlist>> getPlaylistsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(playlistService.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable UUID id) {
        return playlistService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Playlist> createPlaylist(
            @PathVariable UUID userId, @RequestBody Playlist playlist) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playlistService.create(playlist, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(
            @PathVariable UUID id, @RequestBody Playlist playlist) {
        playlist.setId(id);
        return ResponseEntity.ok(playlistService.update(playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable UUID id) {
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}