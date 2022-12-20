package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonFoundedDto;
import com.racers.euphmusic.projection.PersonFoundByUsername;
import org.springframework.stereotype.Component;

@Component
public class PersonFoundedMapper implements Mapper<PersonFoundByUsername, PersonFoundedDto> {

    @Override
    public PersonFoundedDto map(PersonFoundByUsername from) {
        return PersonFoundedDto.builder()
                .username(from.getUsername())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .build();
    }
}
