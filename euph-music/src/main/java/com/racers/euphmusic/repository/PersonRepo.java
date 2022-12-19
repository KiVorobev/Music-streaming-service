package com.racers.euphmusic.repository;

import com.racers.euphmusic.dto.PersonAuthenticationInfo;
import com.racers.euphmusic.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    List<Person> findPersonsByRolesId(Integer roleId);

    Optional<Person> findByUsername(String username);

    Optional<PersonAuthenticationInfo> findProjectionByUsername(String username);

    @Query(value = "SELECT follow (:followerUsername , :followToUsername);", nativeQuery = true)
    void follow(@Param("followerUsername") String fromUsername, @Param("followToUsername") String toUsername);

    @Query(value = "SELECT unfollow (:followerUsername , :followToUsername);", nativeQuery = true)
    void unfollow(@Param("followerUsername") String fromUsername, @Param("followToUsername") String toUsername);

}
