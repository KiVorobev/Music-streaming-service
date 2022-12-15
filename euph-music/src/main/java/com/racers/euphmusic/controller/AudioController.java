package com.racers.euphmusic.controller;

import com.racers.euphmusic.repository.AudioRepo;
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
@SessionAttributes(names = "logged_person")
public class AudioController {

    @Autowired
    private AudioRepo audioRepo;

    @GetMapping("/{id}")
    public String getAudio(@PathVariable Integer id, Model model) {
        return audioRepo.findById(id)
                .map(audio -> {
                    model.addAttribute("audio", audio);
                    return "view/pages/audio";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
