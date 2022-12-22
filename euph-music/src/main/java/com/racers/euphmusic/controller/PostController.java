package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.CommentCreateDto;
import com.racers.euphmusic.dto.PersonLoggedDto;
import com.racers.euphmusic.dto.PostCreateDto;
import com.racers.euphmusic.repository.PersonRepo;
import com.racers.euphmusic.service.CommentService;
import com.racers.euphmusic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/posts")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PersonRepo personRepo;

    @GetMapping("/create")
    public String createPost() {
        return "/view/pages/add_post";
    }

    @GetMapping("/{id}/comments")
    private String findCommentsOnPostByPostId(@PathVariable("id") Integer id, Model model) {
        return postService.findById(id)
                .map(post -> {
                    model.addAttribute("post", post);
                    return "/view/pages/post";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public String create(PostCreateDto postCreateDto, Model model) {
        return personRepo.findByUsername(PersonLoggedDto.getLoggedPersonFromSession(model).getUsername())
                .map(person -> {
                    postService.createPost(postCreateDto, person.getUsername());
                    return "redirect:/persons/" + person.getUsername();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/comments/add")
    public String accComment(@PathVariable("id") Integer postId, CommentCreateDto commentCreateDto, Model model) {
        return commentService.addComment(
                        commentCreateDto.getText(),
                        PersonLoggedDto.getLoggedPersonFromSession(model).getUsername(),
                        postId
                ).map(it -> "redirect:/posts/{id}/comments")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
