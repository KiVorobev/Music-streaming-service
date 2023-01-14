package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.RoleEntity;
import com.racers.euphmusic.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.racers.euphmusic.entity.Role.USER;
import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class PersonCreateMapper implements Mapper<PersonCreateDto, Person> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    @Override
    public Person map(PersonCreateDto from) {
        Person person = new Person();
        Optional.ofNullable(from.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> person.setImage(image.getOriginalFilename()));

        Optional.ofNullable(from.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(person::setPassword);
        person.setUsername(from.getUsername());
        person.setEmail(from.getEmail());
        person.setBalance(0);
        person.setRegistrationDate(LocalDateTime.now());
        person.setRoles(List.of(getBaseRole()));
        return person;
    }

    public RoleEntity getBaseRole() {
        return roleRepo.findRoleEntityByRole(USER);
    }
}
