package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.racers.euphmusic.entity.Role.*;

@Component
@RequiredArgsConstructor
public class PersonCreateMapper implements Mapper<PersonCreateDto, Person> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    @Override
    public void map(PersonCreateDto personCreateDto, Person person) {
        person.setUsername(personCreateDto.getUsername());
        person.setEmail(personCreateDto.getEmail());
        person.setBalance(0);
        person.setRegistrationDate(LocalDateTime.now());
        person.setRoles(List.of(getBaseRole()));
        Optional.ofNullable(personCreateDto.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(person::setPassword);

    }

    public RoleEntity getBaseRole() {
        return roleRepo.findRoleEntityByRole(USER);
    }

}
