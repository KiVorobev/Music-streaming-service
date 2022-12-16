package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes(names = "loggedPerson")
public class LoginController {

    @Autowired
    private PersonService personService;


    @GetMapping("/login")
    public String loginPage() {
        return "view/pages/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        return "view/pages/registration";
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody PersonCreateDto personCreateDto) {
        personService.create(personCreateDto);
        return "redirect:/login";
    }
}
