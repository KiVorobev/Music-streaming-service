package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.service.PersonService;
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
    private PersonService personService;

    @GetMapping("/{username}")
    public String findPersonByUsername(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    boolean isFollowed = person.getFollowers()
                            .stream()
                            .map(Person::getUsername)
                            .toList()
                            .contains(PersonLoggedDto.getLoggedPersonFromSession(model).getUsername());
                    model.addAttribute("isFollowed", isFollowed);
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/saved")
    public String findPersonSavedAudios(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/saved_audios";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/loaded")
    public String findPersonLoadedAudios(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/loaded_audios";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/follows")
    public String findPersonFollows(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/follows";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/follow/{followToUsername}")
    public String followTo(@PathVariable String followToUsername, Model model) {
        return personService.findByUsername(followToUsername)
                .map(followedToPerson -> {
                    personService.follow(PersonLoggedDto.getLoggedPersonFromSession(model), followedToPerson);
                    return "redirect:/persons/" + followToUsername;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/unfollow/{unfollowFromUsername}")
    public String unfollowFrom(@PathVariable String unfollowFromUsername, Model model) {
        return personService.findByUsername(unfollowFromUsername)
                .map(unfollowFromPerson -> {
                    personService.unfollow(PersonLoggedDto.getLoggedPersonFromSession(model), unfollowFromPerson);
                    return "redirect:/persons/" + unfollowFromUsername;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}
