package com.casemodule6_be;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaseModule6BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseModule6BeApplication.class, args);
    }
    //tạo bean cho modelmapper để tiêm sang service
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
