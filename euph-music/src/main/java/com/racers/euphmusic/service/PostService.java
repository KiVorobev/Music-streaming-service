package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PostReadDto;
import com.racers.euphmusic.mapper.PostReadMapper;
import com.racers.euphmusic.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final PostReadMapper postReadMapper;

    public Optional<PostReadDto> findById(Integer id) {
        return postRepo.findById(id)
                .map(postReadMapper::map);
    }
}
