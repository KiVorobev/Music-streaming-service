package com.racers.euphmusic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentReadDto {

    private String text;

    private String publicationDate;

    private PersonShowProfileDto person;
}
