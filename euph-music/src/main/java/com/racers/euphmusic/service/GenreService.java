package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.GenreReadDto;
import com.racers.euphmusic.mapper.GenreReadMapper;
import com.racers.euphmusic.repository.GenreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepo genreRepo;
    private final GenreReadMapper genreReadMapper;

    public List<GenreReadDto> findAll() {
        return genreRepo.findAll().stream()
                .map(genreReadMapper::map)
                .collect(Collectors.toList());
    }
}
