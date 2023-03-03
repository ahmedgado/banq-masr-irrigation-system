package com.banqmasr.coreservice;

import com.banqmasr.coreservice.entities.DBEntity;
import com.banqmasr.coreservice.models.Model;
import jakarta.persistence.Entity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class CoreServiceApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
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
    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApplication.class, args);
    }

}
