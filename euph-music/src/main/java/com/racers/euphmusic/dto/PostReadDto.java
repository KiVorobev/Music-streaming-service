package com.racers.euphmusic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReadDto {

    private Integer id;

    private String description;

    private String publicationDate;

    private PlaylistReadDto playlist;

    private AudioReadDto audio;

    private List<CommentReadDto> comments;
}
