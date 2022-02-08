package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.service.TgInfoService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.employee.dto.TgInfoDto;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TgInfoServiceImpl implements TgInfoService {

    private final ModelMapper mapper = new ModelMapper();
    private final TgInfoRepository tgInfoRepository;


    @Override
    public List<TgInfoDto> getTgInfos() {

        List<TgInfo> tgInfos = tgInfoRepository.findAll();

        return tgInfos.stream().map(tgInfo -> mapper.map(tgInfo, TgInfoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TgInfoDto getTgInfoById(Long id) {
        Optional<TgInfo> byId = tgInfoRepository.findById(id);

        TgInfo tgInfo = byId.orElseThrow(() -> new EntityNotFoundException("TgInfo with id: " + id + " not found"));

        return mapper.map(tgInfo, TgInfoDto.class);
    }

    @Override
    public TgInfoDto create(TgInfoDto tgInfoDto) {
        TgInfo tgInfo = mapper.map(tgInfoDto, TgInfo.class);
        return mapper.map(tgInfo, TgInfoDto.class);
    }

    @Override
    public void deleteById(Long id) {
        tgInfoRepository.deleteById(id);
    }

    @Override
    public TgInfoDto update(Long id, TgInfoDto tgInfoDto) {

        TgInfo tgInfo = mapper.map(tgInfoDto, TgInfo.class);
        tgInfo.setId(id);
        return mapper.map(tgInfoRepository.save(tgInfo), TgInfoDto.class);
    }
}
