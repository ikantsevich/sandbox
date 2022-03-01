package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeService {
    private final HotDeskFeign hotDeskFeign;
    private final ModelMapper mapper;

    public void updateState(String state, EmployeeResponseDto employeeResponseDto) {
        employeeResponseDto.getTgInfo().setChatState(state);
        hotDeskFeign.updateTgInfo(employeeResponseDto.getTgInfo().getId(), mapper.map(employeeResponseDto.getTgInfo(), TgInfoUpdateDto.class));
    }
}
