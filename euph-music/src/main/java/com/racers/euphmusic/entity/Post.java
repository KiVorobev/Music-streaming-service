package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "comments")
@Entity
@Table(schema = "s312762")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @OneToOne
    @JoinColumn
    private Playlist playlist;

    @OneToOne
    @JoinColumn
    private Audio audio;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
