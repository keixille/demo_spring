package com.kangbakso.spring_tutorial.common;

import com.kangbakso.spring_tutorial.dto.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<Object> constructResponse(Object data) {
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseDTO responseDTO = new ResponseDTO() {{
            setData(data);
        }};
        return new ResponseEntity<>(responseDTO, responseHeaders, HttpStatus.OK);
    }
}
