package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostReadDto {

    private Integer id;

    private String description;

    private String publicationDate;

    private PlaylistReadDto playlist;

    private AudioReadDto audio;

    private boolean isOwnedBy;

    private List<CommentReadDto> comments;
}
