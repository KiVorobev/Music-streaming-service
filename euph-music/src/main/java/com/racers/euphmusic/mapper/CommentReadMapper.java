package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.CommentReadDto;
import com.racers.euphmusic.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.racers.euphmusic.utils.LocalDateTimeUtils.*;

@Component
@RequiredArgsConstructor
public class CommentReadMapper implements Mapper<Comment, CommentReadDto> {

    private final AuthorMapper authorMapper;

    @Override
    public CommentReadDto map(Comment from) {
        return CommentReadDto.builder()
                .text(from.getText())
                .formattedPublicationDate(format(from.getPublicationDate()))
                .person(authorMapper.map(from.getPerson()))
                .build();
    }
}
