package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PostReadDto;
import com.racers.euphmusic.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .formattedPublicationDate(format(from.getPublicationDate()))
                .audio(from.getAudio() == null ? null : audioReadMapper.map(from.getAudio()))
                .playlist(from.getPlaylist() == null ? null : from.getPlaylist())
                .comments(
                        from.getComments()
                                .stream().map(commentReadMapper::map)
                                .toList()
                )
                .build();
    }
}
