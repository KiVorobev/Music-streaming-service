package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.CommentReadDto;
import com.racers.euphmusic.mapper.CommentReadMapper;
import com.racers.euphmusic.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final CommentReadMapper commentReadMapper;

    @Transactional
    public Optional<CommentReadDto> addComment(String text, String username, Integer postId) {
        return commentRepo.addComment(text, username, postId)
                .map(commentReadMapper::map);
    }
}
