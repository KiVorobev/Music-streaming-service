package com.racers.euphmusic.dto;

import com.racers.euphmusic.entity.Audio;
import com.racers.euphmusic.entity.Playlist;
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

    private String formattedPublicationDate;

    private Playlist playlist;

    private Audio audio;

    private List<CommentReadDto> comments;
}