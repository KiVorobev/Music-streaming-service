package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.AudioCreateDto;
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

import static com.racers.euphmusic.dto.PersonLoggedDto.getLoggedPersonFromSession;

@Controller
@RequestMapping("/audios")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;
    private final PersonService personService;
    private final GenreService genreService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return audioService.findById(id)
                .map(audio -> {
                    audioService.markIsAudioSaved(audio, getLoggedPersonFromSession(model).getUsername());
                    model.addAttribute("audio", audio);
                    return "view/pages/audio";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String loadAudioCreatePage(Model model) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("persons", personService.getPersonUsernameListDtoWithoutLoggedPerson(getLoggedPersonFromSession(model).getUsername()));
        return "/view/pages/audio_create";
    }

    @PostMapping("/create")
    public String addAudio(AudioCreateDto audioCreateDto, Model model) {
        return audioService.addAudio(audioCreateDto, getLoggedPersonFromSession(model).getUsername())
                .map(dto -> "redirect:/audios/" + dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/save")
    public String saveAudio(@PathVariable("id") Integer id, Model model) {
        String username = getLoggedPersonFromSession(model).getUsername();
        if (!audioService.saveAudio(username, id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return "redirect:/audios/{id}";
    }

    @PostMapping("/{id}/unsave")
    public String removeAudioFromSaved(@PathVariable("id") Integer id, Model model) {
        String username = getLoggedPersonFromSession(model).getUsername();
        if (!audioService.removeAudioFromSaved(username, id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return "redirect:/audios/{id}";
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
