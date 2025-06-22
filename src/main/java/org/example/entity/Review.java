package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String text;
    private Integer score;
    private Boolean isHidden;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "link_track")
    private Track track;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "link_user")
    private User user;
}