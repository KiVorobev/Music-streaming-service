package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Playlist;
import com.racers.euphmusic.projection.PlaylistFoundByName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {

    @Query(value = "SELECT * FROM get_playlists_by_name(:name);", nativeQuery = true)
    List<PlaylistFoundByName> findPlaylistByNameLike(@Param("name") String name);
}
