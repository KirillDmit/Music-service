package org.example.controller;

import org.example.entity.Playlist;
import org.example.service.PlaylistService;
import org.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserService userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Playlist>> getUserPlaylists(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(playlistService.getUserPlaylists(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.save(playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
