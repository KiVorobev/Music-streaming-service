package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.AudioCreateDto;
import com.racers.euphmusic.dto.AudioFoundedDto;
import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.entity.Audio;
import com.racers.euphmusic.mapper.AudioFoundedMapper;
import com.racers.euphmusic.mapper.AudioReadMapper;
import com.racers.euphmusic.repository.AudioRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepo audioRepo;
    private final AudioReadMapper audioReadMapper;
    private final AudioFoundedMapper audioFoundedMapper;
    private final ImageService imageService;

    @Transactional
    public Optional<AudioReadDto> addAudio(AudioCreateDto audioCreateDto, String loggedUsername) {
        audioCreateDto.setAuthors(audioCreateDto.getAuthors() + "," + loggedUsername);
        Optional.ofNullable(audioCreateDto.getImage())
                .ifPresent(this::uploadImage);
        return audioRepo.addAudio(
                audioCreateDto.getName(),
                audioCreateDto.getText(),
                audioCreateDto.getImage() == null
                        ? null
                        : audioCreateDto.getImage().getOriginalFilename(),
                audioCreateDto.getAuthors(),
                audioCreateDto.getGenres()
        ).map(audioReadMapper::map);
    }

    @Transactional
    public boolean saveAudio(String username, Integer audioId) {
        return audioRepo.saveAudio(username, audioId) == 1;
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream(), Audio.class);
        }
    }

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

    public Optional<byte[]> findAvatar(Integer id) {
        return audioRepo.findById(id)
                .map(Audio::getImage)
                .filter(StringUtils::hasText)
                .flatMap(image -> imageService.get(image, Audio.class));
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
