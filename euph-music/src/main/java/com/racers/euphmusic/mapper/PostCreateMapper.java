package com.racers.euphmusic.mapper;

import com.racers.euphmusic.entity.Audio;
import com.racers.euphmusic.entity.Post;
import com.racers.euphmusic.mapper.AudioReadMapper;
import com.racers.euphmusic.mapper.Mapper;
import com.racers.euphmusic.mapper.PostCreateDto;
import com.racers.euphmusic.repository.AudioRepo;
import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PostCreateMapper implements Mapper<PostCreateDto, Post> {

    private final PlaylistRepo playlistRepo;
    private final AudioRepo audioRepo;

    @Override
    public Post map(PostCreateDto from) {
        return Post.builder()
                .audio(from.getAudioId() == null
                        ? null
                        : audioRepo.findById(from.getAudioId()).orElse(null)
                )
                .playlist(from.getPlaylistId() == null
                        ? null
                        : playlistRepo.findById(from.getPlaylistId()).orElse(null)
                ).description(from.getDescription())
                .publicationDate(LocalDateTime.now())
                .build();

    }
}
