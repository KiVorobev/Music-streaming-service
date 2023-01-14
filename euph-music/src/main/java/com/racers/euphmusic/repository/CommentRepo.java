package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM add_comment(:text, :username, :postId); ", nativeQuery = true)
    Optional<Comment> addComment(@Param("text") String text, @Param("username") String username, @Param("postId") Integer postId);
}
