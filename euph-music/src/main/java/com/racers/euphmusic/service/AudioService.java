package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.AudioFoundedDto;
import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.mapper.AudioFoundedMapper;
import com.racers.euphmusic.mapper.AudioReadMapper;
import com.racers.euphmusic.repository.AudioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepo audioRepo;
    private final AudioReadMapper audioReadMapper;
    private final AudioFoundedMapper audioFoundedMapper;

    public List<AudioReadDto> findAll(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("uploadDate").descending());
        return audioRepo.findAllBy(request).stream()
                .map(audioReadMapper::map)
                .collect(toList());
    }

    public List<AudioFoundedDto> finaAllByAuthorName(String authorName) {
        return audioRepo.findAudiosByAuthorName(authorName).stream()
                .map(audioFoundedMapper::map)
                .collect(toList());
    }

    public List<AudioReadDto> findAll() {
        return audioRepo.findAll()
                .stream()
                .map(audioReadMapper::map)
                .toList();
    }

    public Optional<AudioReadDto> findById(Integer id) {
        return audioRepo.findById(id)
                .map(audioReadMapper::map);
    }

}
