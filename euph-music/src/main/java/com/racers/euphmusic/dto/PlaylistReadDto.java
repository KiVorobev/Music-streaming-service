package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PlaylistReadDto {

    private Integer id;

    private String name;

    private String description;

    private String image;

    private boolean isOwnedBy;

    private LocalDate creationDate;

    private List<AudioReadDto> audios;

    private List<PersonShowProfileDto> authors;
}
