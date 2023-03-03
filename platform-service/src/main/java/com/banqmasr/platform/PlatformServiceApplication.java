package com.banqmasr.platform;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class PlatformServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public ModelMapper modelMapper() {
        ModelMapper modelToEntityDtoMapper = new ModelMapper();
        Converter<String, UUID> uuidConverter = new AbstractConverter<String, UUID>() {
            protected UUID convert(String source) {
                return source !=null ? UUID.fromString(source) : null;
            }
        };

        modelToEntityDtoMapper.addConverter(uuidConverter);
        return modelToEntityDtoMapper;
    }
}
