package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Playlist;
import org.example.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserService userService;

    public Playlist create(Playlist playlist, UUID userId) {
        playlist.setUser(userService.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found")));
        return playlistRepository.save(playlist);
    }

    public List<Playlist> findByUserId(UUID userId) {
        return playlistRepository.findByUserId(userId);
    }

    public Optional<Playlist> findById(UUID id) {
        return playlistRepository.findById(id);
    }

    public Playlist update(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void delete(UUID id) {
        playlistRepository.deleteById(id);
    }
}