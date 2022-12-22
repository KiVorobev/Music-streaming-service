package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonReadDto;
import com.racers.euphmusic.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    private final PersonUsernameMapper personUsernameMapper;
    private final AudioReadMapper audioReadMapper;
    private final PostReadMapper postReadMapper;
    private final PlaylistReadMapper playlistReadMapper;

    @Override
    public PersonReadDto map(Person from) {
        return PersonReadDto.builder()
                .username(from.getUsername())
                .status(from.getStatus())
                .description(from.getDescription())
                .balance(from.getBalance())
                .image(from.getImage())
                .followers(
                        Optional.ofNullable(from.getFollowers())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(personUsernameMapper::map)
                                .collect(toList())
                )
                .followTo(
                        Optional.ofNullable(from.getFollowTo())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(personUsernameMapper::map)
                                .collect(toList()))
                .savedAudios(
                        Optional.ofNullable(from.getSavedAudios())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(audioReadMapper::map)
                                .collect(toList()))
                .loadedAudios(
                        Optional.ofNullable(from.getLoadedAudios())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(audioReadMapper::map)
                                .collect(toList())
                )
                .posts(
                        Optional.ofNullable(from.getPosts())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(postReadMapper::map)
                                .collect(toList())
                )
                .playlists(
                        Optional.ofNullable(from.getPlaylists())
                                .stream()
                                .flatMap(Collection::stream)
                                .map(playlistReadMapper::map)
                                .collect(toList())
                )
                .build();

    }
}

