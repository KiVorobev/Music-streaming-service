package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreReadDto {

    private Integer id;

    private String name;
}
