package com.exadel.telegrambot.bot.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.telegrambot.bot.feign.HotDeskFeign;
import com.exadel.telegrambot.bot.utils.EmployeeState;
import com.exadel.telegrambot.bot.utils.KeyboardUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class MessageHandler implements KeyboardUtils, EmployeeState {
    private final ModelMapper mapper;
    private final HotDeskFeign hotDeskFeign;
    private final Executor executor;
    private final KeyboardService keyboardService;
    private final EmployeeService employeeService;


    public void handle(Message message) {
        EmployeeResponseDto employee = null;
        try {
            employee = hotDeskFeign.getEmployeeByChatId(message.getChatId().toString());
        } catch (FeignException e) {
            e.printStackTrace();
            executor.sendMessage(message.getChatId(), "You are not our employee");
            return;
        }

//        Checks if employee hase role
        if (employee.getRoles().size() == 0) {
            executor.sendMessage(message.getChatId(), "You have conflict with your role please connect with admin");
            return;
        }

//        Checks role of employee and directs to ROLE path
        if (employee.getRoles().get(0).getName().equals("employee"))
            employee(message, employee);
        else
            manager(message, employee);
    }

    public void employee(Message message, EmployeeResponseDto employee) {
        switch (message.getText()) {
            case NEW_BOOKING:
                executor.sendMessage(message.getChatId(), "Choose country", keyboardService.countryMenu());
                employeeService.updateState(COUNTRIES, employee);
                break;
            case MY_BOOKINGS:
                break;
            default:
                executor.sendMessage(message.getChatId(), "What do you want today?", keyboardService.homeMenu());
        }
    }

    public void manager(Message message, EmployeeResponseDto employee) {

    }
}
