package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.service.TgInfoService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.employee.dto.TgInfoDto;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TgInfoServiceImpl implements TgInfoService {


    @Autowired
    TgInfoRepository tgInfoRepository;


    @Override
    public List<TgInfo> getTgInfos() {
        return tgInfoRepository.findAll();
    }

    @Override
    public TgInfo getTgInfoById(Long id) {
        Optional<TgInfo> byId = tgInfoRepository.findById(id);

        return byId.orElseThrow(() -> new EntityNotFoundException("TgInfo with id: " + id + " not found"));
    }

    @Override
    public TgInfo create(TgInfo tgInfo) {
        return tgInfoRepository.save(tgInfo);
    }

    @Override
    public void deleteById(Long id) {
        tgInfoRepository.deleteById(id);
    }

    @Override
    public TgInfo update(Long id, TgInfo tgInfo) {
        tgInfo.setId(id);
        return tgInfoRepository.save(tgInfo);
    }

    @Override
    public List<TgInfoDto> toDtoList(List<TgInfo> tgInfos, ModelMapper mapper) {

        return tgInfos.stream().map(tgInfo -> mapper.map(tgInfo, TgInfoDto.class)).collect(Collectors.toList());
    }
}
