package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.AuthorDto;
import com.racers.euphmusic.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<Person, AuthorDto> {

    @Override
    public AuthorDto map(Person from) {
        return AuthorDto.builder()
                .username(from.getUsername())
                .build();
    }
}
