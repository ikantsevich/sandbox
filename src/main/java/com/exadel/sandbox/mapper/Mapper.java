package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.RoleDto;
import com.exadel.sandbox.entities.Role;

public interface Mapper<Entity,Dto>{

    Entity toEntity( Dto dto);
    Dto toDto(Entity entity);
}
