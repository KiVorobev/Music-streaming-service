package com.racers.euphmusic.controller;

import com.racers.euphmusic.dto.CommentCreateDto;
import com.racers.euphmusic.dto.PostCreateDto;
import com.racers.euphmusic.service.CommentService;
import com.racers.euphmusic.service.PersonService;
import com.racers.euphmusic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.racers.euphmusic.dto.PersonLoggedDto.getLoggedPersonFromSession;

@Controller
@RequestMapping("/posts")
@SessionAttributes(names = "loggedPerson")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PersonService personService;

    @GetMapping("/create")
    public String loadCreatePostPage(Model model) {
        String loggedUsername = getLoggedPersonFromSession(model).getUsername();
        return personService.findByUsername(loggedUsername)
                .map(dto -> {
                    dto.getSavedAudios().addAll(dto.getLoadedAudios());
                    model.addAttribute("audios", dto.getSavedAudios());
                    model.addAttribute("playlists", dto.getPlaylists());
                    return "/view/pages/post_create";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public String create(PostCreateDto postCreateDto, Model model) {
        return personService.findByUsername(getLoggedPersonFromSession(model).getUsername())
                .map(person -> {
                    postService.createPost(postCreateDto, person.getUsername());
                    return "redirect:/persons/" + person.getUsername();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Integer id, Model model) {
        String loggedUsername = getLoggedPersonFromSession(model).getUsername();
        if (!postService.deletePost(loggedUsername, id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/persons/" + loggedUsername;
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

    @PostMapping("/{id}/comments/add")
    public String accComment(@PathVariable("id") Integer postId, CommentCreateDto commentCreateDto, Model model) {
        return commentService.addComment(
                        commentCreateDto.getText(),
                        getLoggedPersonFromSession(model).getUsername(),
                        postId
                ).map(it -> "redirect:/posts/{id}/comments")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
