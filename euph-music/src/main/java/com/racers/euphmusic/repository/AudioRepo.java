package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Audio;
import com.racers.euphmusic.projection.AudioFoundByUsername;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AudioRepo extends JpaRepository<Audio, Integer> {

    List<Audio> findAllBy(Pageable pageable);

    @Query(value = "SELECT * FROM get_audios_by_author_name(:authorName);", nativeQuery = true)
    List<AudioFoundByUsername> findAudiosByAuthorName(@Param("authorName") String authorName);

    @Query(value = "SELECT * FROM create_new_audio(:name, :text, :image, :authors);", nativeQuery = true)
    Optional<Audio> addAudio(@Param("name") String audio, @Param("text") String text, @Param("image") String image, @Param("authors") String authors);
}
