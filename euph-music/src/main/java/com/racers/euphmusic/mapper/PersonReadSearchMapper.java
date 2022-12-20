package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonReadSearchDto;
import com.racers.euphmusic.entity.Person;

public class PersonReadSearchMapper implements Mapper<Person, PersonReadSearchDto> {

    @Override
    public PersonReadSearchDto map(Person from) {
        return PersonReadSearchDto.builder()
                .username(from.getUsername())
                .image(from.getImage())
                .build();
    }

}
