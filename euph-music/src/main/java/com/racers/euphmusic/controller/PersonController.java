package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonEditDto;
import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.dto.PersonUsernameDto;
import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
@RequestMapping("/persons")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{username}")
    public String findPersonByUsername(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    boolean isFollowed = isPersonFollowedToAnotherPerson(
                            person.getFollowers(),
                            PersonLoggedDto.getLoggedPersonFromSession(model).getUsername()
                    );
                    model.addAttribute("isFollowed", isFollowed);
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private boolean isPersonFollowedToAnotherPerson(List<PersonUsernameDto> persons, String loggedPersonUsername) {
        return persons.stream()
                .map(PersonUsernameDto::getUsername)
                .anyMatch(username -> username.equals(loggedPersonUsername));

    }

    @PostMapping(value = "/update")
    public String update(Model model, PersonEditDto personEditDto) {
        return personService.update(PersonLoggedDto.getLoggedPersonFromSession(model).getUsername(), personEditDto)
                .map(it -> "view/pages/user_page")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/delete")
    public String delete(Model model) {
        if (!personService.delete(PersonLoggedDto.getLoggedPersonFromSession(model).getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/{username}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("username") String username) {
        return personService.findAvatar(username)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
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

    @GetMapping("/{username}/edit")
    public String loadEditUserPage(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/user_edit";
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
