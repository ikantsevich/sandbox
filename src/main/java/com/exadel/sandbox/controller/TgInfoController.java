package com.exadel.sandbox.controller;

import com.exadel.sandbox.dto.TgInfoDto;
import com.exadel.sandbox.entities.TgInfo;
import com.exadel.sandbox.mapper.TgInfoMapper;
import com.exadel.sandbox.service.TgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("tgInfo")
@RequiredArgsConstructor
public class TgInfoController {
    private final TgInfoMapper mapper;
    private final TgInfoService tgInfoService;

    @GetMapping("list")
    List<TgInfoDto> getTgInfos(){
        List<TgInfo> tgInfos = tgInfoService.getTgInfos();
        return tgInfos.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    TgInfoDto getTgInfoById(@PathVariable("id") int id){
        TgInfo tgInfo = tgInfoService.getTgInfoById(id);
        return mapper.toDto(tgInfo);
    }

    @PutMapping()
    TgInfoDto createTgInfo(@RequestBody TgInfoDto tgInfoDto){
        TgInfo tgInfo = mapper.toEntity(tgInfoDto);
        tgInfo = tgInfoService.create(tgInfo);
        return mapper.toDto(tgInfo);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") int id){
        tgInfoService.deleteById(id);
    }

    @PostMapping("{id}")
    TgInfoDto updateTgInfo(@PathVariable("id") int id,
                           @RequestBody TgInfoDto tgInfoDto){
        TgInfo tgInfo = mapper.toEntity(tgInfoDto);
        tgInfo = tgInfoService.update(id, tgInfo);
        return mapper.toDto(tgInfo);
    }
}
