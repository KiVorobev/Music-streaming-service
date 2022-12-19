package com.racers.euphmusic.mapper;

import com.racers.euphmusic.dto.PersonReadDto;
import com.racers.euphmusic.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.*;

@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    private final PersonUsernameMapper personUsernameMapper;
    private final AudioReadMapper audioReadMapper;
    private final PostReadMapper postReadMapper;

    @Override
    public PersonReadDto map(Person from) {
        return PersonReadDto.builder()
                .username(from.getUsername())
                .status(from.getStatus())
                .description(from.getDescription())
                .followers(
                        from.getFollowers().stream()
                                .map(personUsernameMapper::map)
                                .collect(toList())
                )
                .followTo(
                        from.getFollowTo().stream()
                                .map(personUsernameMapper::map)
                                .collect(toList())
                )
                .savedAudios(
                        from.getSavedAudios().stream()
                                .map(audioReadMapper::map)
                                .collect(toList())
                )
                .loadedAudios(
                        from.getLoadedAudios().stream()
                                .map(audioReadMapper::map)
                                .collect(toList())
                )
                .posts(
                        from.getPosts().stream()
                                .map(postReadMapper::map)
                                .collect(toList())
                )
                .build();

    }
}

