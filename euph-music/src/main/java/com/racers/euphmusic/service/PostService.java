package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PostReadDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.entity.Post;
import com.racers.euphmusic.dto.PostCreateDto;
import com.racers.euphmusic.mapper.PostReadMapper;
import com.racers.euphmusic.mapper.PostCreateMapper;
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
    private final PostCreateMapper postCreateMapper;

    public Optional<PostReadDto> findById(Integer id) {
        return postRepo.findById(id)
                .map(postReadMapper::map);
    }

    @Transactional
    public Optional<PostReadDto> createPost(PostCreateDto postCreateDto, Person person) {
        return Optional.of(postCreateDto)
                .map(dto -> {
                    Post createPost = postCreateMapper.map(postCreateDto);
                    person.addPost(createPost);
                    postRepo.save(createPost);
                    return Optional.of(postReadMapper.map(createPost));
                })
                .orElseThrow();
    }

}
