package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;


@Value
@FieldNameConstants
@Builder
public class PersonCreateDto {

    private String username;

    private String password;

    private String email;


}
