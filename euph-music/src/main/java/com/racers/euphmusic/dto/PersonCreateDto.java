package com.racers.euphmusic.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;


@Value
@FieldNameConstants
public class PersonCreateDto {

    private String username;

    private String password;

    private String email;


}
