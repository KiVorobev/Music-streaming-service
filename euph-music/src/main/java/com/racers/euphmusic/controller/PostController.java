package com.racers.euphmusic.controller;

import com.racers.euphmusic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller("/posts")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}/comments")
    private String findCommentsOnPostByPostId(@PathVariable("id") Integer id, Model model) {
        return postService.findById(id)
                .map(post -> {
                    model.addAttribute("post", post);
                    return "/view/templates/post";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}
