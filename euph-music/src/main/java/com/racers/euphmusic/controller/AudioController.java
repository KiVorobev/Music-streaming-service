package com.racers.euphmusic.controller;

import com.racers.euphmusic.service.AudioService;
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
@RequestMapping("/audios")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return audioService.findById(id)
                .map(audio -> {
                    model.addAttribute("audio", audio);
                    return "view/pages/audio";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
