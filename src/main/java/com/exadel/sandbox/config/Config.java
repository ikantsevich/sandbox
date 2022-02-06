package com.exadel.sandbox.config;

import com.exadel.sandbox.mapper.EmployeeMapper;
import com.exadel.sandbox.mapper.TgInfoMapper;
import com.exadel.sandbox.mapper.VacationMapper;
import com.exadel.sandbox.mapper.impl.EmployeeMapperImpl;
import com.exadel.sandbox.mapper.impl.TgInfoMapperImpl;
import com.exadel.sandbox.mapper.impl.VacationMapperImpl;
import com.exadel.sandbox.service.EmployeeService;
import com.exadel.sandbox.service.TgInfoService;
import com.exadel.sandbox.service.VacationService;
import com.exadel.sandbox.service.impl.EmployeeServiceImpl;
import com.exadel.sandbox.service.impl.TgInfoServiceImpl;
import com.exadel.sandbox.service.impl.VacationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {
    @Bean
    EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    EmployeeMapper employeeMapper() {
        return new EmployeeMapperImpl();
    }

    @Bean
    TgInfoService tgInfoService(){
        return new TgInfoServiceImpl();
    }

    @Bean
    TgInfoMapper tgInfoMapper() {
        return new TgInfoMapperImpl();
    }

    @Bean
    VacationService vacationService() {
        return new VacationServiceImpl();
    }

    @Bean
    VacationMapper vacationMapper() {
        return new VacationMapperImpl();
    }

}
