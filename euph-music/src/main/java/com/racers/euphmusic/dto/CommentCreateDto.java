package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateDto {

    private String text;
}
