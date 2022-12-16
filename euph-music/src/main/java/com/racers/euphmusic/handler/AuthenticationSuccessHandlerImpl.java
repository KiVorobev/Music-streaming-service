package com.racers.euphmusic.handler;

import com.racers.euphmusic.dto.PersonReadDto;
import com.racers.euphmusic.entity.Person;
import com.racers.euphmusic.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    PersonRepo personRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        Person person;
        String username = ((User) authentication.getPrincipal()).getUsername();
        person = personRepo.findByUsername(username).get();
        PersonReadDto personReadDto = PersonReadDto.builder()
                .id(person.getId())
                .username(person.getUsername())
                .email(person.getEmail())
                .balance(person.getBalance())
                .build();
        session.setAttribute("loggedPerson", personReadDto);
        response.sendRedirect("/persons/" + personReadDto.getUsername());
    }
}
