package com.kangbakso.spring_tutorial.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDTO {
    private String first_name;

    private String last_name;
}
