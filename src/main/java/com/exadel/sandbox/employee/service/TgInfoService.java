package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;

import java.util.List;

public interface TgInfoService {
    List<TgInfoResponseDto> getTgInfos();

    TgInfoResponseDto getTgInfoById(Long id);

    TgInfoResponseDto create(TgInfoCreateDto tgInfoCreateDto);

    void deleteById(Long id);

    TgInfoResponseDto update(Long id, TgInfoUpdateDto tgInfoUpdateDto);

}
