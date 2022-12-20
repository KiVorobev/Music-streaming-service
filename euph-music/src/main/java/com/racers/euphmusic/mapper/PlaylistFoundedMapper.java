package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.projection.PlaylistFoundByName;
import org.springframework.stereotype.Component;

@Component
public class PlaylistFoundedMapper implements Mapper<PlaylistFoundByName, PlaylistFoundedDto> {

    @Override
    public PlaylistFoundedDto map(PlaylistFoundByName from) {
        return PlaylistFoundedDto.builder()
                .id(from.getId())
                .name(from.getName())
                .image(from.getImage() == null
                        ? null
                        : from.getImage())
                .build();
    }
}
