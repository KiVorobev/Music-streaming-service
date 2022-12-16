package com.racers.euphmusic.dto;

import com.racers.euphmusic.entity.Person;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AudioReadDto {

    private Integer id;

    private String name;

    private String text;

    private LocalDateTime uploadDate;

    private List<Person> authors;
}
