package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoBaseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.employee.service.TgInfoService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TgInfoServiceImpl implements TgInfoService {

    private final ModelMapper mapper;
    private final TgInfoRepository tgInfoRepository;

    @Override
    @Transactional
    public List<TgInfoResponseDto> getTgInfos() {
        List<TgInfo> tgInfos = tgInfoRepository.findAll();

        return tgInfos.stream().map(tgInfo -> mapper.map(tgInfo, TgInfoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TgInfoResponseDto getTgInfoById(Long id) {
        Optional<TgInfo> byId = tgInfoRepository.findById(id);

        TgInfo tgInfo = byId.orElseThrow(() -> new EntityNotFoundException("TgInfo with id: " + id + " not found"));


        return mapper.map(tgInfo, TgInfoResponseDto.class);
    }

    @Override
    @Transactional
    public TgInfoResponseDto create(TgInfoCreateDto tgInfoCreateDto) {
        TgInfo tgInfo = mapper.map(mapper.map(tgInfoCreateDto, TgInfoBaseDto.class), TgInfo.class);
        return mapper.map(tgInfo, TgInfoResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tgInfoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TgInfoResponseDto update(Long id, TgInfoUpdateDto tgInfoUpdateDto) {
        TgInfo tgInfo = mapper.map(mapper.map(tgInfoUpdateDto, TgInfoBaseDto.class), TgInfo.class);
        tgInfo.setId(id);
        return mapper.map(tgInfoRepository.save(tgInfo), TgInfoResponseDto.class);
    }
}
