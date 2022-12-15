package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {
}
