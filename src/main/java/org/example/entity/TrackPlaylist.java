package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_track_playlist")
public class TrackPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;



    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "link_track")
    private Track track;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "link_playlist")
    private Playlist playlist;

    private Integer position;
}