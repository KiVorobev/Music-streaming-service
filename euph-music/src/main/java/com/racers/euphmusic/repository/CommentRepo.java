package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment , Long> {

}
