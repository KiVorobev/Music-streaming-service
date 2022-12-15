package com.racers.euphmusic.controller;

import com.racers.euphmusic.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/{username}")
    public String getPerson(@PathVariable String username, Model model) {
        return personRepo.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
