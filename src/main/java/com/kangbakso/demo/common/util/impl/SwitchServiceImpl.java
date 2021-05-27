package com.kangbakso.demo.common.util.impl;

import com.kangbakso.demo.common.util.SwitchService;
import org.springframework.stereotype.Service;

@Service
public class SwitchServiceImpl implements SwitchService {
    public String switching(int num) {
        String result;

        switch(num) {
            case 0:
                result = "this is 0";
                break;
            case 1:
                result = "this is 1";
                break;
            case 2:
                result = "this is 2";
                break;
            default :
                result = "this is default";
        }

        return result;
    }
}
