package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Audio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AudioRepo extends JpaRepository<Audio, Integer> {

    List<Audio> findAllBy(Pageable pageable);
}
