package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonReadDto {

    private String username;

    private String status;

    private String description;

    private String image;

    private Integer balance;

    private List<PersonUsernameDto> followers;

    private List<PersonUsernameDto> followTo;

    private List<AudioReadDto> loadedAudios;

    private List<AudioReadDto> savedAudios;

    private List<PostReadDto> posts;

    private List<PlaylistReadDto> playlists;


}
