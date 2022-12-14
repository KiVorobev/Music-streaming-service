package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo extends CrudRepository<Playlist, Integer> {
}
