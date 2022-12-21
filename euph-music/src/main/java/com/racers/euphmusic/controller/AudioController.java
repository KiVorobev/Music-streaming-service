package com.racers.euphmusic.controller;

import com.racers.euphmusic.service.AudioService;
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

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("username") Integer id) {
        return audioService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }


}
