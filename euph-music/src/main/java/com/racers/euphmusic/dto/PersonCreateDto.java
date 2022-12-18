package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;


@Value
@FieldNameConstants
@Builder
public class PersonCreateDto {

    private String username;

    private String password;

    private String email;

    private MultipartFile image;

}
