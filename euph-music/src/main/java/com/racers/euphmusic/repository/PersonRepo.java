package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Integer> {

    List<Person> findPersonsByRolesId(Integer roleId);

    Optional<Person> findByUsername(String username);
}
