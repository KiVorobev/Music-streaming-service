package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonFoundedDto {

    private String username;

    private String image;
}
