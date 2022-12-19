package com.racers.euphmusic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonReadDto {

    private String username;

    private String status;

    private String description;

    private List<PersonUsernameDto> followers;

    private List<PersonUsernameDto> followTo;

    private List<AudioReadDto> loadedAudios;

    private List<AudioReadDto> savedAudios;

    private List<PostReadDto> posts;


}
