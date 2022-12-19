package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PersonEditDto {

    private String status;

    private String description;

    private MultipartFile image;
}
