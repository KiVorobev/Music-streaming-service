package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM add_post(:username, :playlistId, :audioId, :description);", nativeQuery = true)
    Optional<Post> createPost(@Param("username") String username,
                              @Param("playlistId") @Nullable Integer playlistId,
                              @Param("audioId") @Nullable Integer audioId,
                              @Param("description") String description);

    @Query(value = "SELECT * FROM delete_post(:username, :postId);", nativeQuery = true)
    Integer deletePost(@Param("username") String username, @Param("postId") Integer postId);

}
