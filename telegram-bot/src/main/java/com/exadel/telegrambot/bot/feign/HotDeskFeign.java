package com.exadel.telegrambot.bot.feign;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.telegrambot.bot.utils.TelegramUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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

    @PutMapping("office/{id}/free-seats")
    List<SeatResponseDto> getSeatsByOfficeIdAndDate(
            @PathVariable Long id,
            @RequestBody List<LocalDate> dateList
    );

    @GetMapping("parking-spot/office-id/{id}")
    List<ParkingSpotResponseDto> getParkingSpotByOfficeId(@PathVariable Long id);

    @PutMapping("office/{id}/free-spots")
    List<ParkingSpotResponseDto> getFreeParkingSpots(
            @PathVariable Long id,
            @RequestBody List<LocalDate> dates
    );

    @GetMapping("seat/{id}")
    SeatResponseDto getSeatById(@PathVariable Long id);
}
