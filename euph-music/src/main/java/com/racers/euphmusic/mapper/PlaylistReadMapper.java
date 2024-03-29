package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PlaylistReadDto;
import com.racers.euphmusic.entity.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaylistReadMapper implements Mapper<Playlist, PlaylistReadDto> {

    private final AudioReadMapper audioReadMapper;
    private final PersonShowProfileMapper personShowProfileMapper;

    @Override
    public PlaylistReadDto map(Playlist from) {
        return PlaylistReadDto.builder()
                .name(from.getName())
                .id(from.getId())
                .name(from.getName())
                .description(from.getDescription())
                .creationDate(from.getCreationDate())
                .image(from.getImage())
                .audios(from.getAudios().stream()
                        .map(audioReadMapper::map)
                        .collect(Collectors.toList()))
                .authors(from.getAuthors().stream()
                        .map(personShowProfileMapper::map)
                        .collect(Collectors.toList()))
                .build();
    }
}
