package com.exadel.sandbox.config;

import com.exadel.sandbox.mapper.AttachmentMapper;
import com.exadel.sandbox.mapper.FloorMapper;
import com.exadel.sandbox.mapper.OfficeMapper;
import com.exadel.sandbox.mapper.impl.AttachmentMapperImpl;
import com.exadel.sandbox.mapper.impl.FloorMapperImpl;
import com.exadel.sandbox.mapper.impl.OfficeMapperImpl;
import com.exadel.sandbox.service.AttachmentService;
import com.exadel.sandbox.service.FloorService;
import com.exadel.sandbox.service.OfficeService;
import com.exadel.sandbox.service.impl.AttachmentServiceImpl;
import com.exadel.sandbox.service.impl.FloorServiceImpl;
import com.exadel.sandbox.service.impl.OfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class ConfigOfficeFloorAttachment {
    @Bean
    OfficeMapper officeMapper(){
        return new OfficeMapperImpl();
    }

    @Bean
    FloorMapper floorMapper(){
        return new FloorMapperImpl();
    }

    @Bean
    OfficeService officeService(){
        return new OfficeServiceImpl();
    }

    @Bean
    AttachmentService attachmentService(){
        return new AttachmentServiceImpl();
    }

    @Bean
    FloorService floorService(){
        return new FloorServiceImpl();
    }

    @Bean
    AttachmentMapper attachmentMapper(){
        return new AttachmentMapperImpl();
    }


}
