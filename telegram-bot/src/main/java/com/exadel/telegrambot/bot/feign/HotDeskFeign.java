package com.exadel.telegrambot.bot.feign;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.telegrambot.bot.utils.TelegramUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "HotDeskFeign", url = TelegramUtils.HOT_DESK_URL)
public interface HotDeskFeign {
    @GetMapping("employee/chat-id/{chatId}")
    EmployeeResponseDto getEmployeeByChatId(@PathVariable String chatId);

    @GetMapping("address/list")
    List<AddressResponseDto> getAddresses();

    @PutMapping("tg-info/{id}")
    void updateTgInfo(@PathVariable Long id, TgInfoUpdateDto tgInfoUpdateDto);

    @GetMapping("office/address/{id}")
    OfficeResponseDto getOfficeByAddressId(@PathVariable Long id);
}
