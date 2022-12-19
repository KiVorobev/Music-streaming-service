package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@Controller
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class LoginController {

    private final PersonService personService;

    @GetMapping("/login")
    public String loginPage() {
        return "view/pages/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        return "view/pages/registration";
    }

    @PostMapping(value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(PersonCreateDto personCreateDto) {
        return Optional.of(personCreateDto)
                .map(personService::create)
                .map(it -> "redirect:/login")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
