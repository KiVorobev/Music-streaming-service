package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.CommentReadDto;
import com.racers.euphmusic.entity.Comment;

import static com.racers.euphmusic.utils.LocalDateTimeUtils.*;

public class CommentReadMapper implements Mapper<Comment, CommentReadDto> {

    @Override
    public CommentReadDto map(Comment from) {
        return CommentReadDto.builder()
                .text(from.getText())
                .formattedPublicationDate(format(from.getPublicationDate()))
                .person(from.getPerson())
                .build();
    }
}
