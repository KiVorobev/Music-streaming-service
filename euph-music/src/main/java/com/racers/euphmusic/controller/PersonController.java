package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PersonEditDto;
import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.mapper.PersonReadMapper;
import com.racers.euphmusic.service.PersonService;
import com.racers.euphmusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.racers.euphmusic.dto.PersonLoggedDto.getLoggedPersonFromSession;

@Controller
@RequestMapping("/persons")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PlaylistService playlistService;
    private final PersonReadMapper personReadMapper;

    @GetMapping("/{username}")
    public String findPersonByUsername(@PathVariable String username, Model model) {
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    model.addAttribute("isFollowed", personService.isPersonFollowedToAnotherPerson(
                            person.getFollowers(),
                            getLoggedPersonFromSession(model).getUsername()));
                    return "view/pages/user_page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/edit")
    public String loadEditUserPage(@PathVariable String username, Model model) {
        if (!getLoggedPersonFromSession(model).getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.LOCKED);
        }
        return personService.findByUsername(username)
                .map(person -> {
                    model.addAttribute("person", person);
                    return "view/pages/user_edit";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/update")
    public String update(Model model, PersonEditDto personEditDto, HttpServletRequest request) {
        String loggedUsername = getLoggedPersonFromSession(model).getUsername();
        return personService.update(loggedUsername, personEditDto)
                .map(it -> {
                    request.getSession().setAttribute("loggedPerson",
                            PersonLoggedDto.builder()
                                    .username(it.getUsername())
                                    .image(it.getImage())
                                    .build());
                    return "redirect:/persons/" + loggedUsername;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/delete")
    public String delete(Model model) {
        if (!personService.delete(getLoggedPersonFromSession(model).getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/";
    }

    @GetMapping("/{username}/playlists")
    public String loadPlaylistsPage(@PathVariable String username, Model model) {
        return Optional.ofNullable(playlistService.findAllByAuthorName(username))
                .map(playlistReadDtos -> {
                    model.addAttribute("playlists", playlistReadDtos);
                    return "/view/pages/playlists";
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
                    personService.follow(getLoggedPersonFromSession(model), followedToPerson);
                    return "redirect:/persons/" + followToUsername;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/unfollow/{unfollowFromUsername}")
    public String unfollowFrom(@PathVariable String unfollowFromUsername, Model model) {
        return personService.findByUsername(unfollowFromUsername)
                .map(unfollowFromPerson -> {
                    personService.unfollow(getLoggedPersonFromSession(model), unfollowFromPerson);
                    return "redirect:/persons/" + unfollowFromUsername;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("username") String username) {
        return personService.findAvatar(username)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/avatar")
    public ResponseEntity<byte[]> findDefaultAvatar() {
        return personService.findDefaultAvatar()
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}