package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping(value = "/registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String register(PersonCreateDto personCreateDto) {
        personService.create(personCreateDto);
        return "redirect:/login";
    }
}
