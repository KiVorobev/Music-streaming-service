package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AudioReadDto {

    private Integer id;

    private String name;

    private String text;

    private String uploadDate;

    private List<PersonUsernameDto> authors;
}
