package com.racers.euphmusic.controller;

import com.racers.euphmusic.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final PlaylistRepo playlistRepo;

    @GetMapping("/{id}")
    public String findPlaylistById(@PathVariable Integer id, Model model) {
        return playlistRepo.findById(id)
                .map(playlist -> {
                    model.addAttribute("playlist", playlist);
                    return "view/pages/playlist";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
