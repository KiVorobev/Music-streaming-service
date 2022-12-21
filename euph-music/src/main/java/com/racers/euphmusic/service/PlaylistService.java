package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.entity.Playlist;
import com.racers.euphmusic.mapper.PlaylistFoundedMapper;
import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final ImageService imageService;
    private final PlaylistRepo playlistRepo;
    private final PlaylistFoundedMapper playlistFoundedMapper;

    public Optional<Playlist> findById(Integer id) {
        return playlistRepo.findById(id);
    }

    public List<PlaylistFoundedDto> findPlaylistByNameLike(String name) {
        return playlistRepo.findPlaylistByNameLike(name).stream()
                .map(playlistFoundedMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<byte[]> findAvatar(Integer id) {
        return playlistRepo.findById(id)
                .map(Playlist::getImage)
                .filter(StringUtils::hasText)
                .flatMap(image -> imageService.get(image, Playlist.class));
    }
}
