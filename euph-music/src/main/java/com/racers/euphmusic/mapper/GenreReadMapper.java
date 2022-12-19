package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.GenreReadDto;
import com.racers.euphmusic.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreReadMapper implements Mapper<Genre, GenreReadDto> {

    @Override
    public GenreReadDto map(Genre from) {
        return GenreReadDto.builder()
                .id(from.getId())
                .name(from.getName())
                .build();
    }
}
