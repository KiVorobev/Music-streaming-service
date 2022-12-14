package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "comments")
@Builder
@Table(schema = "s312762")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne
    private Person person;

    @Column(name = "playlist_id")
    private Integer playlistId;

    @Column(name = "audio_id")
    private Integer audioId;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}
