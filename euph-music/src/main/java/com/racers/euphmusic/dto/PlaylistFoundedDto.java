package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaylistFoundedDto {

    private Integer id;

    private String name;

    private String image;
}
