package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonCreateDto;
import com.racers.euphmusic.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    private PersonService personService;

    @GetMapping("/login")
    public String loginPage() {
        return "authorization";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestBody PersonCreateDto personCreateDto) {
        personService.create(personCreateDto);
        return "redirect:/login";
    }
}
