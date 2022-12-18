package com.racers.euphmusic.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "s312762")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;
}
