package org.example.service;

import org.example.entity.Track;
import org.example.repository.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackServiceTest {

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackService trackService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTracks_returnsListOfTracks() {
        Track track1 = new Track(1L, "Title1", "Artist1", "Album1", "Pop", new BigDecimal("4.5"), 2020, 50, null);
        Track track2 = new Track(2L, "Title2", "Artist2", "Album2", "Rock", new BigDecimal("3.8"), 2019, 60, null);
        when(trackRepository.findAll()).thenReturn(List.of(track1, track2));

        List<Track> result = trackService.getAllTracks();

        assertEquals(2, result.size());
        verify(trackRepository, times(1)).findAll();
    }

    @Test
    void getById_returnsTrack_whenExists() {
        Track track = new Track(1L, "Title", "Artist", "Album", "Pop", new BigDecimal("4.0"), 2015, 45, null);
        when(trackRepository.findById(1L)).thenReturn(Optional.of(track));

        Optional<Track> result = trackService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Title", result.get().getTitle());
    }

    @Test
    void getById_returnsEmpty_whenNotExists() {
        when(trackRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Track> result = trackService.getById(99L);

        assertFalse(result.isPresent());
    }
}
