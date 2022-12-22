package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Playlist;
import com.racers.euphmusic.projection.PlaylistFoundByName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {

    @Query(value = "SELECT * FROM get_playlists_by_name(:name);", nativeQuery = true)
    List<PlaylistFoundByName> findPlaylistByNameLike(@Param("name") String name);

    @Query(value = "SELECT * FROM create_playlist(:playlistName, :description, :image, :audios, :authors);", nativeQuery = true)
    Optional<Playlist> createPlaylist(@Param("playlistName") String playlistName,
                                      @Param("description") String description,
                                      @Param("image") String image,
                                      @Param(("audios")) String audios,
                                      @Param(("authors")) String authors);

    @Query(value = "SELECT * FROM get_playlists_by_username(:authorName);", nativeQuery = true)
    List<Playlist> findAllByAuthorName(@Param("authorName") String authorName);
}
