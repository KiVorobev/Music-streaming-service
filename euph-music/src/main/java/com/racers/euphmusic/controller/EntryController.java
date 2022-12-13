package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.UserReadDto;
import com.racers.euphmusic.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class EntryController {

    @GetMapping("/registration")
    public String getRegPage() {
        return "registration";
    }

    @GetMapping("/authorization")
    public String getAuthPage() {
        return "authorization";
    }

    @PostMapping("/registration")
    public String register(@RequestBody UserReadDto userReadDto) {
        System.out.println(userReadDto);
        return "registration";
    }

    @PostMapping("/authorization")
    public String authorize(@RequestBody UserReadDto userReadDto) {
        System.out.println(userReadDto);
        return "authorization";
    }

}
