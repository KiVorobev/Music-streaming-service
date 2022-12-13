package com.racers.euphmusic.controller;

import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/test/{id}")
    public String getPerson(@PathVariable Integer id, Model model) {
        try {
            Optional<Person> maybePerson = userRepo.findById(id);
            maybePerson.ifPresent(person -> model.addAttribute("person", person));
            return "test";
        } catch (Exception e) {
            return "error";
        }
    }

}
