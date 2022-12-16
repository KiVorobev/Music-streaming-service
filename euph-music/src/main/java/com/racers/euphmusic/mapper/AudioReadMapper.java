package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.entity.Audio;
import org.springframework.stereotype.Component;

@Component
public class AudioReadMapper implements Mapper<Audio, AudioReadDto> {


    @Override
    public AudioReadDto map(Audio from) {
        return AudioReadDto.builder()
                .id(from.getId())
                .name(from.getName())
                .text(from.getText())
                .uploadDate(from.getUploadDate())
                .authors(from.getAuthors())
                .build();
    }
}
