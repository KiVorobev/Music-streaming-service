package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.PlaylistCreateDto;
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

import static com.racers.euphmusic.dto.PersonLoggedDto.getLoggedPersonFromSession;


@Controller
@RequestMapping("/playlists")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PersonService personService;

    @GetMapping("/create")
    public String loadPlaylistCreatePage(Model model) {
        String loggedUsername = getLoggedPersonFromSession(model).getUsername();
        return personService.findByUsername(loggedUsername).
                map(personReadDto -> {
                    model.addAttribute("persons", personService.getPersonUsernameListDtoWithoutLoggedPerson(loggedUsername));
                    model.addAttribute("audios", personReadDto.getSavedAudios().addAll(personReadDto.getLoadedAudios()));
                    return "/view/pages/playlist_create";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public String createPlaylist(PlaylistCreateDto playlistCreateDto, Model model) {
        String loggedUsername = getLoggedPersonFromSession(model).getUsername();
        return playlistService.createPlaylist(playlistCreateDto, loggedUsername)
                .map(dto -> "redirect:/perons/" + loggedUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public String findPlaylistById(@PathVariable Integer id, Model model) {
        return playlistService.findById(id)
                .map(playlist -> {
                    model.addAttribute("playlist", playlist);
                    return "view/pages/playlist";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("username") Integer id) {
        return playlistService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
