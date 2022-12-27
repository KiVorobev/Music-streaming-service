package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonShowProfileDto;
import com.racers.euphmusic.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonShowProfileMapper implements Mapper<Person, PersonShowProfileDto> {

    @Override
    public PersonShowProfileDto map(Person from) {
        return PersonShowProfileDto.builder()
                .username(from.getUsername())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .build();
    }
}
