package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(schema = "s312762")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"audios", "authors"})
@EqualsAndHashCode(of = "id")
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String image;

    private LocalDate creationDate;

    @ManyToMany
    @JoinTable(
            name = "playlist_audio",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> audios;

    @ManyToMany
    @JoinTable(
            name = "playlist_author",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Person> authors;
}
