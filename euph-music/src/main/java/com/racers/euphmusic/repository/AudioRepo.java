package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepo extends JpaRepository<Audio, Integer> {
}
