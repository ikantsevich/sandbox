package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.TgInfoDto;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.service.TgInfoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tg-info")
@RequiredArgsConstructor
public class TgInfoController {
    private final ModelMapper mapper = new ModelMapper();
    private final TgInfoService tgInfoService;

    @GetMapping("list")
    List<TgInfoDto> getTgInfos(){
        return tgInfoService.toDtoList(tgInfoService.getTgInfos(), mapper);
    }

    @GetMapping("{id}")
    TgInfoDto getTgInfoById(@PathVariable("id") Long id){
        return mapper.map(tgInfoService.getTgInfoById(id), TgInfoDto.class);
    }

    @PostMapping()
    TgInfoDto createTgInfo(@RequestBody TgInfoDto tgInfoDto){
        TgInfo tgInfo = mapper.map(tgInfoDto, TgInfo.class);

        return mapper.map(tgInfoService.create(tgInfo), TgInfoDto.class);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id){
        tgInfoService.deleteById(id);
    }

    @PutMapping("{id}")
    TgInfoDto updateTgInfo(@PathVariable("id") Long id,
                           @RequestBody TgInfoDto tgInfoDto){

        TgInfo tgInfo = mapper.map(tgInfoDto, TgInfo.class);

        return mapper.map(tgInfoService.update(id, tgInfo), TgInfoDto.class);
    }
}
