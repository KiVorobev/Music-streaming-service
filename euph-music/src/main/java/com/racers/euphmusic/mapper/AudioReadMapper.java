package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.entity.Audio;
import com.racers.euphmusic.utils.LocalDateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AudioReadMapper implements Mapper<Audio, AudioReadDto> {

    private final AuthorMapper authorAudioMapper;

    @Override
    public AudioReadDto map(Audio from) {
        return AudioReadDto.builder()
                .id(from.getId())
                .name(from.getName())
                .text(from.getText())
                .formattedUploadDate(LocalDateTimeUtils.format(from.getUploadDate()))
                .authors(
                        from.getAuthors().stream()
                                .map(authorAudioMapper::map)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
