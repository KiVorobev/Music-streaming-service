package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.ui.Model;

import java.util.Optional;

@Data
@Builder
public class PersonLoggedDto {

    private String username;

    private String image;

    public static PersonLoggedDto getLoggedPersonFromSession(Model model) {
        return Optional.of((PersonLoggedDto) model.getAttribute("loggedPerson"))
                .orElse(null);
    }
}
