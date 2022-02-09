package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import com.exadel.sandbox.employee.service.TgInfoService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class TgInfoServiceImpl implements TgInfoService {

    private final ModelMapper mapper;
    private final TgInfoRepository tgInfoRepository;

    @Override
    public List<TgInfoResponseDto> getTgInfos() {
        List<TgInfo> tgInfos = tgInfoRepository.findAll();

        return tgInfos.stream().map(tgInfo -> mapper.map(tgInfo, TgInfoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public TgInfoResponseDto getTgInfoById(Long id) {
        Optional<TgInfo> byId = tgInfoRepository.findById(id);

        TgInfo tgInfo = byId.orElseThrow(() -> new EntityNotFoundException("TgInfo with id: " + id + " not found"));


        return mapper.map(tgInfo, TgInfoResponseDto.class);
    }

    @Override
    public TgInfoResponseDto create(TgInfoCreateDto tgInfoCreateDto) {
        TgInfo tgInfo = mapper.map(tgInfoCreateDto, TgInfo.class);

        return mapper.map(tgInfoRepository.save(tgInfo), TgInfoResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        tgInfoRepository.deleteById(id);
    }

    @Override
    public TgInfoResponseDto update(Long id, TgInfoUpdateDto tgInfoUpdateDto) {
        TgInfo tgInfo = tgInfoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TgInfo with id: " + id + " not found"));

        mapper.map(tgInfoUpdateDto, tgInfo);

        return mapper.map(tgInfoRepository.save(tgInfo), TgInfoResponseDto.class);
    }
}
