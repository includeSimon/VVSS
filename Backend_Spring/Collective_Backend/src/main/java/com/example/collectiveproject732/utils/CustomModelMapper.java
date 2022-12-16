package com.example.collectiveproject732.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomModelMapper extends ModelMapper {

    public ModelMapper modelMapperBean() {

        return new ModelMapper();
    }
}
