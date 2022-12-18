package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.Role;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.mapper.PersonCreateMapper;
import com.racers.euphmusic.repository.PersonRepo;
import com.racers.euphmusic.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService implements UserDetailsService {

    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;
    private final PersonCreateMapper personCreateMapper;
    private final ImageService imageService;

    @Transactional
    public Person create(PersonCreateDto personCreateDto) {
        uploadImage(personCreateDto.getImage());
        Person person = personCreateMapper.map(personCreateDto);
        personRepo.save(person);
        return person;
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findAvatar(String username) {
        return personRepo.findByUsername(username)
                .map(Person::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    public Optional<Person> findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Transactional
    public void follow(PersonLoggedDto from, Person to) {
        personRepo.follow(from.getUsername(), to.getUsername());
    }

    @Transactional
    public void unfollow(PersonLoggedDto from, Person to) {
        personRepo.unfollow(from.getUsername(), to.getUsername());
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
