package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonReadDto {

    private Integer id;

    private String username;

    private String email;

    private Integer balance;
}
