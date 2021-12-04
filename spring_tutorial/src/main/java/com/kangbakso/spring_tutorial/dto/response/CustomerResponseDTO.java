package com.kangbakso.spring_tutorial.dto.response;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    @NotNull
    private String customer_id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;
}
