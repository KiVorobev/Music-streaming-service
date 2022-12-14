package com.racers.euphmusic;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.repository.PersonRepo;
import com.racers.euphmusic.repository.RoleRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EuphMusicApplicationTests {

    @Autowired
    PersonRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Test
    void getAllRolesByPersonId() {
        List<RoleEntity> allRolesByPersonsId = roleRepo.findAllRolesByPersonsId(2);
        List<Person> personsByRolesId = userRepo.findPersonsByRolesId(2);
        Person person = personsByRolesId.get(1);
        System.out.println();
    }

    @Test
    void getUserById() {
        Optional<Person> maybePerson = userRepo.findById(2);
        maybePerson.ifPresent(System.out::println);
    }

    @Test
    void contextLoads() {
    }

}
