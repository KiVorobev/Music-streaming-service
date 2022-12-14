package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post , Integer> {
}
