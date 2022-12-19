package com.racers.euphmusic.dto;

import com.racers.euphmusic.entity.Person;
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

    private String formattedPublicationDate;

    private AuthorDto person;

}
