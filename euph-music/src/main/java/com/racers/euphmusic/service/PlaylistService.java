package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.PlaylistCreateDto;
import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.dto.PlaylistReadDto;
import com.racers.euphmusic.entity.Playlist;
import com.racers.euphmusic.mapper.PlaylistFoundedMapper;
import com.racers.euphmusic.mapper.PlaylistReadMapper;
import com.racers.euphmusic.repository.PersonRepo;
import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final ImageService imageService;
    private final PlaylistRepo playlistRepo;
    private final PersonRepo personRepo;
    private final PlaylistFoundedMapper playlistFoundedMapper;
    private final PlaylistReadMapper playlistReadMapper;

    @Transactional
    public Optional<PlaylistReadDto> createPlaylist(PlaylistCreateDto playlistCreateDto, String loggedUsername) {
        Optional.ofNullable(playlistCreateDto.getImage())
                .ifPresent(this::uploadImage);
        return playlistRepo.createPlaylist(
                        playlistCreateDto.getName(),
                        playlistCreateDto.getDescription(),
                        playlistCreateDto.getImage().getOriginalFilename(),
                        playlistCreateDto.getAudios(),
                        playlistCreateDto.getAuthors() == null
                                ? loggedUsername
                                : playlistCreateDto.getAuthors() + "," + loggedUsername
                )
                .map(playlistReadMapper::map);
    }

    @Transactional
    public boolean deletePlaylist(String authorName, Integer playlistId) {
        return playlistRepo.deletePlaylist(authorName, playlistId) == 1;
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream(), Playlist.class);
        }
    }

    public Optional<PlaylistReadDto> findById(Integer id) {
        return playlistRepo.findById(id)
                .map(playlistReadMapper::map);
    }

    public List<PlaylistReadDto> findAllByAuthorName(String authorName) {
        return playlistRepo.findAllByAuthorName(authorName).stream()
                .map(playlistReadMapper::map)
                .collect(Collectors.toList());
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

    public PlaylistReadDto markIsOwnedByLoggedUser(PlaylistReadDto playlistReadDto, String loggedUsername) {
        return personRepo.findByUsername(loggedUsername)
                .map(it -> {
                    boolean contains = it.getPlaylists().stream()
                            .map(Playlist::getId)
                            .toList()
                            .contains(playlistReadDto.getId());
                    playlistReadDto.setOwnedBy(contains);
                    return playlistReadDto;
                })
                .orElse(null);
    }
}
