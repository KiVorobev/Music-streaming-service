package com.racers.euphmusic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class SearchController {

    @GetMapping("/search")
    public String getSearchPage() {
        return "view/pages/search";
    }
}
