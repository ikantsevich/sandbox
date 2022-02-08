package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.TgInfoDto;
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
    List<TgInfoDto> getTgInfos() {
        return tgInfoService.getTgInfos();
    }

    @GetMapping("{id}")
    TgInfoDto getTgInfoById(@PathVariable("id") Long id) {
        return tgInfoService.getTgInfoById(id);
    }

    @PostMapping()
    TgInfoDto createTgInfo(@RequestBody TgInfoDto tgInfoDto) {

        return tgInfoService.create(tgInfoDto);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id) {
        tgInfoService.deleteById(id);
    }

    @PutMapping("{id}")
    TgInfoDto updateTgInfo(@PathVariable("id") Long id,
                           @RequestBody TgInfoDto tgInfoDto) {


        return tgInfoService.update(id, tgInfoDto);
    }
}
