package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Audio;
import org.springframework.data.repository.CrudRepository;

public interface AudioRepo extends CrudRepository<Audio , Integer> {
}
