package com.racers.euphmusic.controller;

import com.racers.euphmusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/playlists")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

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
