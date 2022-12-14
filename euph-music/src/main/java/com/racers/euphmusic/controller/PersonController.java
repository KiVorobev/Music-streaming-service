package com.racers.euphmusic.controller;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/{username}")
    public String getPerson(@PathVariable String username, Model model) {
        try {
            Optional<Person> maybePerson = personRepo.findByUsername(username);
            maybePerson.ifPresent(person -> model.addAttribute("person", person));
            return "view/pages/user_page";
        } catch (Exception e) {
            return "error";
        }
    }

}
