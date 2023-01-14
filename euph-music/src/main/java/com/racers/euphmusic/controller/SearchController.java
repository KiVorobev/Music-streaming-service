package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.AudioFoundedDto;
import com.racers.euphmusic.dto.PersonFoundedDto;
import com.racers.euphmusic.dto.PlaylistFoundedDto;
import com.racers.euphmusic.service.AudioService;
import com.racers.euphmusic.service.PersonService;
import com.racers.euphmusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class SearchController {

    private final PersonService personService;
    private final AudioService audioService;
    private final PlaylistService playlistService;

    @GetMapping("/search")
    public String getSearchPage(String text, Model model) {
        List<PersonFoundedDto> personFoundedDtos = personService.findUsersByUsernameLike(text);
        List<AudioFoundedDto> audioFoundedDtos = audioService.finaAllByAuthorName(text);
        List<PlaylistFoundedDto> playlistFoundedDtos = playlistService.findPlaylistByNameLike(text);
        model.addAttribute("persons", personFoundedDtos);
        model.addAttribute("audios", audioFoundedDtos);
        model.addAttribute("playlists", playlistFoundedDtos);
        return "view/pages/search";
    }
}
