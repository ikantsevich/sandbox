package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.dto.TgInfoDto;
import com.exadel.sandbox.employee.entity.TgInfo;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface TgInfoService {
    List<TgInfo> getTgInfos();

    TgInfo getTgInfoById(Long id);

    TgInfo create(TgInfo tgInfo);

    void deleteById(Long id);

    TgInfo update(Long id, TgInfo tgInfo);

    List<TgInfoDto> toDtoList(List<TgInfo> tgInfos, ModelMapper mapper);
}
