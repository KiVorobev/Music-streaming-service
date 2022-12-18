package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre , Integer> {

    Optional<Genre> findByName(String name);
}
