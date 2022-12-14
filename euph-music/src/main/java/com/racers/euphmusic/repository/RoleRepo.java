package com.racers.euphmusic.repository;

import com.racers.euphmusic.entity.Role;
import com.racers.euphmusic.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepo extends CrudRepository<RoleEntity, Integer> {

    List<RoleEntity> findAllRolesByPersonsId(Integer personId);

    RoleEntity findRoleEntityByRole(Role role);

}
