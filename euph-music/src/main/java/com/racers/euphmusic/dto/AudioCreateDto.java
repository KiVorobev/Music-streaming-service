package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class AudioCreateDto {

    private String name;

    private String text;

    private MultipartFile image;

    private List<String> authors;

    private List<GenreReadDto> genres;

}
