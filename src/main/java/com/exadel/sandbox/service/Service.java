package com.exadel.sandbox.service;


import com.exadel.sandbox.dto.PermissionDto;
import com.exadel.sandbox.entities.Permission;

import java.util.List;

public interface Service <Dto>{
    List<Dto> getAll();
    Dto getById(Integer id);
    Dto create(Dto dto);
    void deleteById(Integer id);
    Dto updateById(Integer id, Dto dto);

}
