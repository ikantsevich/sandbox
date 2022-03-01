package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.utils.EmployeeState;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@AllArgsConstructor
public class CallbackQueryHandler implements EmployeeState {
    private final ModelMapper mapper;
    private final HotDeskFeign hotDeskFeign;
    private final Executor executor;
    private final KeyboardService keyboardService;
    private final EmployeeService employeeService;

    public void handle(CallbackQuery callbackQuery) {
        EmployeeResponseDto employee = null;
        try {
            employee = hotDeskFeign.getEmployeeByChatId(callbackQuery.getMessage().getChatId().toString());
        } catch (FeignException e) {
            e.printStackTrace();
            System.out.println(callbackQuery.getMessage().getChatId());
            executor.sendMessage(callbackQuery.getMessage().getChatId(), "You are not our employee");
            return;
        }

        switch (employee.getTgInfo().getChatState()) {
            case COUNTRIES:
                executor.updateMessage(
                        callbackQuery.getMessage().getChatId(),
                        callbackQuery.getMessage().getMessageId(),
                        "choose city",
                        keyboardService.cityMenu(callbackQuery.getData()));

                employeeService.updateState(CITIES, employee);
                break;
            case CITIES:
                executor.updateMessage(
                        callbackQuery.getMessage().getChatId(),
                        callbackQuery.getMessage().getMessageId(),
                        "choose office",
                        keyboardService.officeMenu(callbackQuery.getData())
                );
                employeeService.updateState(OFFICE, employee);
                break;
            case OFFICE:

        }
    }
}
