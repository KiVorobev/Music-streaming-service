package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.ui.Model;
import java.util.Optional;

@Data
@Builder
public class PersonLoggedDto {

    private Integer id;

    private String username;

    private String email;

    private Integer balance;

    public static PersonLoggedDto getLoggedPersonFromSession(Model model) {
        return Optional.of((PersonLoggedDto) model.getAttribute("loggedPerson"))
                .orElse(null);
    }
}
