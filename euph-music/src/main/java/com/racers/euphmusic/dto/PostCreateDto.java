package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateDto {

    private String description;

    private Integer playlistId;

    private Integer audioId;
}
