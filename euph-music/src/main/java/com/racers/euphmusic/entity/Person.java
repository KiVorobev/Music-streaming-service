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
@ToString(exclude = {"roles", "posts", "comments", "followers" , "followTo"})
@Builder
@Table(schema = "s312762")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    private Integer balance;

    private String status;

    private String description;

    @ManyToMany(cascade = CascadeType.REMOVE)
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

    @OneToMany(mappedBy = "person")
    private List<Post> posts;

    @OneToMany(mappedBy = "person")
    private List<Comment> comments;

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getPersons().add(this);
    }

}
