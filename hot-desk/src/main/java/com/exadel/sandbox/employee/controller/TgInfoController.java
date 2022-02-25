package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.employee.service.TgInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tg-info")
@RequiredArgsConstructor
public class TgInfoController {
    private final TgInfoService tgInfoService;

    @GetMapping("list")
    ResponseEntity<List<TgInfoResponseDto>> getTgInfos() {
        return tgInfoService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<TgInfoResponseDto> getTgInfoById(@PathVariable("id") Long id) {
        return tgInfoService.getById(id);
    }

    @PostMapping()
    ResponseEntity<TgInfoResponseDto> createTgInfo(@RequestBody TgInfoCreateDto tgInfoCreateDto) {
        return tgInfoService.create(tgInfoCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id) {
        tgInfoService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<TgInfoResponseDto> updateTgInfo(@PathVariable("id") Long id,
                                   @RequestBody TgInfoUpdateDto tgInfoUpdateDto) {

        return tgInfoService.update(id, tgInfoUpdateDto);
    }
}
