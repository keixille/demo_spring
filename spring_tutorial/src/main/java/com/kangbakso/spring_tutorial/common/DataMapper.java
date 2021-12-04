package com.kangbakso.spring_tutorial.common;

public interface DataMapper <E, D> {
    D convertToDTO(E entity);
    E convertToEntity(D dto);
}
