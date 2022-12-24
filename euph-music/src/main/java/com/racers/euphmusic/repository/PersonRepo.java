package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.projection.PersonFoundByUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    List<Person> findPersonsByRolesId(Integer roleId);

    Optional<Person> findByUsername(String username);

    <T> Optional<T> findByUsername(String username, Class<T> clazz);

    @Query(value = "SELECT * FROM get_persons_by_username (:username);", nativeQuery = true)
    List<PersonFoundByUsername> findUsersByUsernameLike(@Param("username") String username);

    @Query(value = "SELECT follow (:followerUsername , :followToUsername);", nativeQuery = true)
    void follow(@Param("followerUsername") String fromUsername, @Param("followToUsername") String toUsername);

    @Query(value = "SELECT unfollow (:followerUsername , :followToUsername);", nativeQuery = true)
    void unfollow(@Param("followerUsername") String fromUsername, @Param("followToUsername") String toUsername);
}
