package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
}
