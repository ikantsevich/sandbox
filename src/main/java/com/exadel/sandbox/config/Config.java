package com.exadel.sandbox.config;

import com.exadel.sandbox.mapper.AttachmentMapper;
import com.exadel.sandbox.mapper.FloorMapper;
import com.exadel.sandbox.mapper.OfficeMapper;
import com.exadel.sandbox.mapper.impl.AttachmentMapperImpl;
import com.exadel.sandbox.mapper.impl.FloorMapperImpl;
import com.exadel.sandbox.mapper.impl.OfficeMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class Config {
    @Bean
    OfficeMapper officeMapper(){
        return new OfficeMapperImpl();
    }

    @Bean
    FloorMapper floorMapper(){
        return new FloorMapperImpl();
    }

    @Bean
    AttachmentMapper attachmentMapper(){
        return new AttachmentMapperImpl();
    }
}
