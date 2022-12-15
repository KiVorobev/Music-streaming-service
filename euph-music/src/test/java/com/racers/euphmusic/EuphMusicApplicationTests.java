package com.racers.euphmusic;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.Playlist;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EuphMusicApplicationTests {

    @Autowired
    PersonRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PlaylistRepo playlistRepo;
    @Autowired
    AudioRepo audioRepo;
    @Autowired
    PostRepo postRepo;

    @Test
    @Transactional
    void getPostById(){
        postRepo.findById(1);
        System.out.println("");
    }

    @Test
    void getAudioById(){
        audioRepo.findById(1);
        System.out.println("");
    }
    
    @Test
    void getPlaylistById(){
        Optional<Playlist> maybePlaylist = playlistRepo.findById(1);
        System.out.println("");
    }

    @Test
    void getAllRolesByPersonId() {
        List<RoleEntity> allRolesByPersonsId = roleRepo.findAllRolesByPersonsId(2);
        List<Person> personsByRolesId = userRepo.findPersonsByRolesId(2);
        Person person = personsByRolesId.get(1);
        System.out.println();
    }

    @Test
    @Transactional
    void getUserById() {
        Optional<Person> maybePerson = userRepo.findById(4);
        maybePerson.ifPresent(System.out::println);
    }

    @Test
    void contextLoads() {
    }

}
