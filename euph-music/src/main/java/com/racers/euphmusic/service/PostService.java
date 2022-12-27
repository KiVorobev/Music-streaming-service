package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PostCreateDto;
import com.racers.euphmusic.dto.PostReadDto;
import com.racers.euphmusic.entity.Post;
import com.racers.euphmusic.mapper.PostReadMapper;
import com.racers.euphmusic.repository.PersonRepo;
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
    private final PersonRepo personRepo;
    private final PostReadMapper postReadMapper;

    public Optional<PostReadDto> findById(Integer id) {
        return postRepo.findById(id)
                .map(postReadMapper::map);
    }

    @Transactional
    public Optional<PostReadDto> createPost(PostCreateDto postCreateDto, String username) {
        return
                postRepo.createPost(username,
                                postCreateDto.getPlaylistId() == null
                                        ? null
                                        : postCreateDto.getPlaylistId(),
                                postCreateDto.getAudioId() == null
                                        ? null
                                        : postCreateDto.getAudioId(),
                                postCreateDto.getDescription())
                        .map(postReadMapper::map);

    }

    @Transactional
    public boolean deletePost(String username, Integer postId) {
        return postRepo.deletePost(username, postId) == 1;
    }

    public PostReadDto markIsOwnedByLoggedUser(PostReadDto postReadDto, String loggedUsername) {
        return personRepo.findByUsername(loggedUsername)
                .map(it -> {
                    boolean contains = it.getPosts().stream()
                            .map(Post::getId)
                            .toList()
                            .contains(postReadDto.getId());
                    postReadDto.setOwnedBy(contains);
                    return postReadDto;
                })
                .orElse(null);
    }
}
