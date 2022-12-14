package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.Role;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.mapper.PersonCreateMapper;
import com.racers.euphmusic.repository.PersonRepo;
import com.racers.euphmusic.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PersonCreateMapper personCreateMapper;

    public Person create(PersonCreateDto personCreateDto) {
        Person person = new Person();
        personCreateMapper.map(personCreateDto, person);
        personRepo.save(person);
        return person;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> maybeUser = personRepo.findByUsername(username);
        Integer personId = maybeUser.map(user -> user.getId()).get();
        List<RoleEntity> allRolesByPersonsId = roleRepo.findAllRolesByPersonsId(personId);
        Set<Role> rolesSet = allRolesByPersonsId
                .stream()
                .map(RoleEntity::getRole)
                .collect(Collectors.toSet());
        return maybeUser
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        rolesSet
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
