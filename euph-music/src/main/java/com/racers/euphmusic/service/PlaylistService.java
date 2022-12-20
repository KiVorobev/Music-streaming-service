package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.mapper.PlaylistFoundedMapper;
import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepo playlistRepo;
    private final PlaylistFoundedMapper playlistFoundedMapper;

    public List<PlaylistFoundedDto> findPlaylistByNameLike(String name) {
        return playlistRepo.findPlaylistByNameLike(name).stream()
                .map(playlistFoundedMapper::map)
                .collect(Collectors.toList());
    }
}
