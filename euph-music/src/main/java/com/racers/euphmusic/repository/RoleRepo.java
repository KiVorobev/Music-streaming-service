package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Role;
import com.racers.euphmusic.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    List<RoleEntity> findAllRolesByPersonsId(Integer personId);

    RoleEntity findRoleEntityByRole(Role role);
}
