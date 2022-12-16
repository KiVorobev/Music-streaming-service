package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "audiosOfThisGenre")
@Table(schema = "s312762")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany()
    @JoinTable(
            name = "genre_audio",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> audiosOfThisGenre;
}
