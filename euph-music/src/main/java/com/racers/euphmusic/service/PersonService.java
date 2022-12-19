package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.*;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.Role;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.mapper.PersonCreateMapper;
import com.racers.euphmusic.mapper.PersonEditMapper;
import com.racers.euphmusic.mapper.PersonReadMapper;
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
    private final PersonReadMapper personReadMapper;
    private final PersonEditMapper personEditMapper;

    @Transactional
    public PersonReadDto create(PersonCreateDto personCreateDto) {
        return Optional.of(personCreateDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    Person person = personCreateMapper.map(dto);
                    personRepo.save(person);
                    return personReadMapper.map(person);
                })
                .orElseThrow();
    }

    @Transactional
    public Optional<PersonReadDto> update(String username, PersonEditDto personEditDto) {
        return Optional.ofNullable(personEditDto)
                .map(dto -> {
                    uploadImage(personEditDto.getImage());
                    Person person = personRepo.findByUsername(username).orElse(null);
                    personEditMapper.map(personEditDto, person);
                    personRepo.saveAndFlush(person);
                    return personReadMapper.map(person);
                });
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

    @Transactional
    public boolean delete(String username) {
        return personRepo.findByUsername(username)
                .map(entity -> {
                    personRepo.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public Optional<PersonReadDto> findByUsername(String username) {
        return personRepo.findByUsername(username)
                .map(personReadMapper::map);
    }

    @Transactional
    public void follow(PersonLoggedDto from, PersonReadDto to) {
        personRepo.follow(from.getUsername(), to.getUsername());
    }

    @Transactional
    public void unfollow(PersonLoggedDto from, PersonReadDto to) {
        personRepo.unfollow(from.getUsername(), to.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonAuthenticationInfo> maybePersonAuthInfo = personRepo.findProjectionByUsername(username);
        Integer personId = maybePersonAuthInfo.map(PersonAuthenticationInfo::id).get();
        List<RoleEntity> allRolesByPersonsId = roleRepo.findAllRolesByPersonsId(personId);
        Set<Role> rolesSet = allRolesByPersonsId
                .stream()
                .map(RoleEntity::getRole)
                .collect(Collectors.toSet());
        return maybePersonAuthInfo
                .map(user -> new User(
                        user.username(),
                        user.password(),
                        rolesSet
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
