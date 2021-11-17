package com.kangbakso.spring_tutorial.common;

public interface DataMapper <Entity, DTO> {
    DTO convertToDTO(Entity entity);
    Entity convertToEntity(DTO dto);
}
