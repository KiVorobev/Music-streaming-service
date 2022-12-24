package com.racers.euphmusic.entity;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"roles", "posts", "followers", "followTo", "loadedAudios", "savedAudios"})
@EqualsAndHashCode(of = "id")
@Entity
@Table(schema = "s312762")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(updatable = false)
    private String password;

    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Generated(GenerationTime.ALWAYS)
    private Integer balance;

    private String status;

    private String description;

    private String image;

    @ManyToMany
    @JoinTable(
            name = "role_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    @ManyToMany
    @JoinTable(
            name = "person_follow",
            joinColumns = @JoinColumn(name = "follow_to_person_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_person_id")
    )
    private List<Person> followers;

    @ManyToMany
    @JoinTable(
            name = "person_follow",
            joinColumns = @JoinColumn(name = "follower_person_id"),
            inverseJoinColumns = @JoinColumn(name = "follow_to_person_id")
    )
    private List<Person> followTo;

    @ManyToMany
    @JoinTable(
            name = "author_audio",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> loadedAudios;

    @ManyToMany
    @JoinTable(
            name = "save_audio",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
    )
    private List<Audio> savedAudios;

    @OneToMany(mappedBy = "person")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "playlist_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private List<Playlist> playlists;
}