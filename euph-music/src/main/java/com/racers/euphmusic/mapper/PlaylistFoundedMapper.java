package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.projection.PlaylistFoundByName;
import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaylistFoundedMapper implements Mapper<PlaylistFoundByName, PlaylistFoundedDto> {

    private final PersonShowProfileMapper personShowProfileMapper;
    private final PlaylistRepo playlistRepo;

    @Override
    public PlaylistFoundedDto map(PlaylistFoundByName from) {
        return PlaylistFoundedDto.builder()
                .id(from.getId())
                .name(from.getName())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .authors(
                        playlistRepo.findById(from.getId()).get()
                                .getAuthors().stream()
                                .map(personShowProfileMapper::map)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
