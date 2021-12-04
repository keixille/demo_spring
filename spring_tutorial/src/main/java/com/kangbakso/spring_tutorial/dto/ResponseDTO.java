package com.kangbakso.spring_tutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    @NotNull
    @JsonProperty("data")
    private Object data;
}
