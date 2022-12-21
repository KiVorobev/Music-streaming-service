package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "s312762")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String image;

    @Column(name = "creation_date")
    private LocalDate date;
}
