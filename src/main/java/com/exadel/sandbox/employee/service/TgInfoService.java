package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.dto.TgInfoDto;

import java.util.List;

public interface TgInfoService {
    List<TgInfoDto> getTgInfos();

    TgInfoDto getTgInfoById(Long id);

    TgInfoDto create(TgInfoDto tgInfoDto);

    void deleteById(Long id);

    TgInfoDto update(Long id, TgInfoDto tgInfoDto);

}
