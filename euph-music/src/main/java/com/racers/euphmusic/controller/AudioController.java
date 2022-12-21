package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.AudioCreateDto;
import com.racers.euphmusic.dto.GenreReadDto;
import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.dto.PersonUsernameDto;
import com.racers.euphmusic.service.AudioService;
import com.racers.euphmusic.service.GenreService;
import com.racers.euphmusic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/audios")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;
    private final PersonService personService;
    private final GenreService genreService;

    @GetMapping("/create")
    public String loadAddAudioCreatePage(Model model) {
        List<GenreReadDto> genreReadDtos = genreService.findAll();
        List<PersonUsernameDto> personReadDtos = personService.findAll();
        model.addAttribute("genres", genreReadDtos);
        model.addAttribute("persons", personReadDtos);
        return "/view/pages/audio_create";
    }

    @PostMapping("/create")
    public String addAudio(AudioCreateDto audioCreateDto, Model model) {
        audioCreateDto.setAuthors(audioCreateDto.getAuthors() + "," + PersonLoggedDto.getLoggedPersonFromSession(model).getUsername());
        return audioService.addAudio(audioCreateDto)
                .map(dto -> "redirect:/audios/" + dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/save")
    public String saveAudio(@PathVariable("id") Integer id, Model model) {
        String username = PersonLoggedDto.getLoggedPersonFromSession(model).getUsername();
        if (!audioService.saveAudio(username, id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return "redirect:/persons/" + username + "/saved";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return audioService.findById(id)
                .map(audio -> {
                    model.addAttribute("audio", audio);
                    return "view/pages/audio";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Integer id) {
        return audioService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }


}
