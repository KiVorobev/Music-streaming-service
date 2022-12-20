package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.List;

@Controller
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class StartPageController {

    private final AudioService audioService;

    @GetMapping("/")
    public String loadStartPage(Model model) {
        List<AudioReadDto> audios = audioService.findAll(0, 5);
        model.addAttribute("audios", audios);
        return "view/pages/main";
    }
}
