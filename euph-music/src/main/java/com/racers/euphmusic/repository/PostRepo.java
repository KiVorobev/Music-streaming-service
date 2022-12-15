package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post , Integer> {
}
