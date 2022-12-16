package com.racers.euphmusic.controller;

import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/persons")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{username}")
    public String getPerson(@PathVariable String username, Model model, HttpServletRequest httpServletRequest) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
