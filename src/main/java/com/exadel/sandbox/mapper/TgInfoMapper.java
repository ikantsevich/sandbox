package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.EmployeeDto;
import com.exadel.sandbox.dto.TgInfoDto;
import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.entities.TgInfo;
import com.exadel.sandbox.mapper.impl.TgInfoMapperImpl;

public interface TgInfoMapper {
    TgInfo toEntity(TgInfoDto tgInfoDto);

    TgInfoDto toDto(TgInfo tgInfo);

}
