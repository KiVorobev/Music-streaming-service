package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlaylistFoundedDto {

    private Integer id;

    private String name;

    private String image;

    private List<PersonUsernameDto> authors;
}
