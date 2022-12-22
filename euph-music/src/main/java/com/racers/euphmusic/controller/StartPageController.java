package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.service.AudioService;
import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.racers.euphmusic.dto.PersonLoggedDto.getLoggedPersonFromSession;

@Controller
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class StartPageController {

    private final AudioService audioService;
    private final PersonService personService;

    @GetMapping("/")
    public String loadStartPage(Model model) {
        List<AudioReadDto> audios = audioService.findAll(0, 5);
        return personService.findByUsername(getLoggedPersonFromSession(model).getUsername())
                .map(loggedDto -> {
                    audioService.markAllSavedAudios(loggedDto, audios);
                    model.addAttribute("audios", audios);
                    return "view/pages/main";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
