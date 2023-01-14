package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PlaylistCreateDto {

    private String name;

    private String description;

    private MultipartFile image;

    private String audios;

    private String authors;
}
