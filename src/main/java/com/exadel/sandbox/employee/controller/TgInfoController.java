package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.employee.service.TgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tg-info")
@RequiredArgsConstructor
public class TgInfoController {
    private final TgInfoService tgInfoService;

    @GetMapping("list")
    List<TgInfoResponseDto> getTgInfos() {
        return tgInfoService.getTgInfos();
    }

    @GetMapping("{id}")
    TgInfoResponseDto getTgInfoById(@PathVariable("id") Long id) {
        return tgInfoService.getTgInfoById(id);
    }

    @PostMapping()
    TgInfoResponseDto createTgInfo(@RequestBody TgInfoCreateDto tgInfoCreateDto) {
        return tgInfoService.create(tgInfoCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id) {
        tgInfoService.deleteById(id);
    }

    @PutMapping("{id}")
    TgInfoResponseDto updateTgInfo(@PathVariable("id") Long id,
                           @RequestBody TgInfoUpdateDto tgInfoUpdateDto) {

        return tgInfoService.update(id, tgInfoUpdateDto);
    }
}
