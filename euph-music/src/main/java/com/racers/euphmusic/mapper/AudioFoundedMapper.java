package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.AudioFoundedDto;
import com.racers.euphmusic.dto.PersonShowProfileDto;
import com.racers.euphmusic.projection.AudioFoundByUsername;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class AudioFoundedMapper implements Mapper<AudioFoundByUsername, AudioFoundedDto> {

    @Override
    public AudioFoundedDto map(AudioFoundByUsername from) {
        return AudioFoundedDto
                .builder()
                .id(from.getId())
                .name(from.getName())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .authors(Arrays.stream(from.getAuthors().split(","))
                        .map(String::trim)
                        .map(username -> PersonShowProfileDto.builder().username(username).build())
                        .collect(Collectors.toList()))
                .build();
    }
}
