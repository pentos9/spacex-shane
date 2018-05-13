package com.buzz.common.entity;

import org.modelmapper.ModelMapper;

public class EntityUtils {
    public static Object dtoToEntity(Object dto, Object entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entity.getClass());
    }
}
