package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PostReadDto;
import com.racers.euphmusic.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.racers.euphmusic.utils.LocalDateTimeUtils.*;

@Component
@RequiredArgsConstructor
public class PostReadMapper implements Mapper<Post, PostReadDto> {

    private final CommentReadMapper commentReadMapper;
    private final AudioReadMapper audioReadMapper;

    @Override
    public PostReadDto map(Post from) {
        return PostReadDto.builder()
                .id(from.getId())
                .description(from.getDescription())
                .publicationDate(format(from.getPublicationDate()))
                .audio(from.getAudio() == null ? null : audioReadMapper.map(from.getAudio()))
                .playlist(from.getPlaylist() == null ? null : from.getPlaylist())
                .comments(Optional.ofNullable(from.getComments())
                                .stream()
                                        .flatMap(Collection::stream)
                                                .map(commentReadMapper::map)
                                                        .collect(Collectors.toList())
                )
                .build();
    }
}
