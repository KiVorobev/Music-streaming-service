package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonReadDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/persons")
@SessionAttributes(names = "loggedPerson")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/{username}")
    public String getPerson(@PathVariable String username, Model model) {
        return personRepo.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    PersonReadDto loggedPerson = (PersonReadDto) model.getAttribute("loggedPerson");
                    boolean isFollowed = person.getFollowers()
                            .stream()
                            .map(Person::getUsername)
                            .toList()
                            .contains(loggedPerson.getUsername());
                    model.addAttribute("isFollowed", isFollowed);
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/saved")
    public String getPersonSaved(@PathVariable String username, Model model) {
        return personRepo.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/saved_audios";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/loaded")
    public String getPersonLoaded(@PathVariable String username, Model model) {
        return personRepo.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/loaded_audios";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/follows")
    public String getPersonFollows(@PathVariable String username, Model model) {
        return personRepo.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/follows";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
