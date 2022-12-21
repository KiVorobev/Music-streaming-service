package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonUsernameDto;
import com.racers.euphmusic.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonUsernameMapper implements Mapper<Person, PersonUsernameDto> {

    @Override
    public PersonUsernameDto map(Person from) {
        return PersonUsernameDto.builder()
                .username(from.getUsername())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .build();
    }
}
