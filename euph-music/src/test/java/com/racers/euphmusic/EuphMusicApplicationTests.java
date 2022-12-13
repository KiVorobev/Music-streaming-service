package com.racers.euphmusic;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.UserRepo;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EuphMusicApplicationTests {

    @Autowired
    UserRepo userRepo;

    @Test
    void getUserById() {

        Optional<Person> maybePerson = userRepo.findById(2);
        maybePerson.ifPresent(System.out::println);
    }

    @Test
    void contextLoads() {
    }

}
