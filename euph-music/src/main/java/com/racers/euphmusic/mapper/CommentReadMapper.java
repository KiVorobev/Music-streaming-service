package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.CommentReadDto;
import com.racers.euphmusic.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.racers.euphmusic.utils.LocalDateTimeUtils.format;

@Component
@RequiredArgsConstructor
public class CommentReadMapper implements Mapper<Comment, CommentReadDto> {

    private final PersonShowProfileMapper personShowProfileMapper;

    @Override
    public CommentReadDto map(Comment from) {
        return CommentReadDto.builder()
                .text(from.getText())
                .publicationDate(format(from.getPublicationDate()))
                .person(personShowProfileMapper.map(from.getPerson()))
                .build();
    }
}
