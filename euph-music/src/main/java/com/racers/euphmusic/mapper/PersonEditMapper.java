package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonEditDto;
import com.racers.euphmusic.entity.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
public class PersonEditMapper implements Mapper<PersonEditDto, Person> {

    @Override
    public Person map(PersonEditDto from, Person to) {
        copy(from, to);
        return to;
    }

    @Override
    public Person map(PersonEditDto from) {
        return new Person();
    }

    private void copy(PersonEditDto from, Person person) {
        person.setStatus(from.getStatus() == null ? person.getStatus() : from.getStatus());
        person.setDescription(from.getDescription() == null ? person.getDescription() : from.getDescription());
        Optional.ofNullable(from.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> person.setImage(image.getOriginalFilename()));
    }
}
