package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import dtos.employee.dto.tgInfoDto.TgInfoCreateDto;
import dtos.employee.dto.tgInfoDto.TgInfoResponseDto;
import dtos.employee.dto.tgInfoDto.TgInfoUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TgInfoService extends BaseCrudService<TgInfo, TgInfoResponseDto, TgInfoUpdateDto, TgInfoCreateDto, TgInfoRepository> {
    public TgInfoService(ModelMapper mapper, TgInfoRepository repository) {
        super(mapper, repository);
    }
}
