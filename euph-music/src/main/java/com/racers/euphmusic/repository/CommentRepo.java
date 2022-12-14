package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment , Long> {
}
