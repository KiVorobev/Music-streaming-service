package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Person, Integer> {
}
