package org.example.controller;

import org.example.entity.Track;
import org.example.service.TrackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TrackController.class)
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackService trackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTracks_returnsListOfTracks() throws Exception {
        List<Track> mockTracks = List.of(
                new Track(1L, "Track 1", "Artist 1", "Album 1", "Pop", new BigDecimal("4.0"), 2015, 70, null),
                new Track(2L, "Track 2", "Artist 2", "Album 2", "Rock", new BigDecimal("3.5"), 2017, 120, null)
        );

        given(trackService.getAllTracks()).willReturn(mockTracks);

        mockMvc.perform(get("/api/tracks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Track 1"));
    }

    @Test
    void getTrackById_whenExists_returnsTrack() throws Exception {
        Track mockTrack = new Track(1L, "Track 1", "Artist 1", "Album 1", "Pop", new BigDecimal("4.0"), 2016, 180,  null);
        given(trackService.getById(1L)).willReturn(Optional.of(mockTrack));

        mockMvc.perform(get("/api/tracks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Track 1"));
    }

    @Test
    void getTrackById_whenNotExists_returns404() throws Exception {
        given(trackService.getById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/tracks/99"))
                .andExpect(status().isNotFound());
    }
}
