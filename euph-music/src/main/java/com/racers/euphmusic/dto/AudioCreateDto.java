package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AudioCreateDto {

    private String name;

    private String text;

    private MultipartFile image;

    private String authors;

    private String genres;

}
