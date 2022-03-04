package com.exadel.sandbox.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableSwagger2
public class Config {

    @Bean
    AuditorAware<String> auditorProvider() {
        return Optional::empty;
    }

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDate.class), typeResolver.resolve(List.class, java.sql.Date.class)),
                        AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDateTime.class), typeResolver.resolve(List.class, java.util.Date.class))
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
