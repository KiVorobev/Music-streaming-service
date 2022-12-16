package com.racers.euphmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = "loggedPerson")
public class StartPageController {

    @GetMapping("/")
    public String loadStartPage() {
        return "view/pages/main";
    }
}
