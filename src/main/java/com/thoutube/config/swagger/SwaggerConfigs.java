package com.thoutube.config.swagger;

import com.amazonaws.services.appstream.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigs {
    
    @Bean
    public Docket thoutubeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.thoutube"))
                        .paths(PathSelectors.ant("/**"))
                        .build()
                        .ignoredParameterTypes(User.class);
    }
}