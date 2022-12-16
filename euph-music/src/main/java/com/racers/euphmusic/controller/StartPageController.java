package com.racers.euphmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    @GetMapping("/")
    public String loadStartPage() {
        return "view/pages/main";
    }


}
