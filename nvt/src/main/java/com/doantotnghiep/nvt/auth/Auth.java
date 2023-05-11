package com.doantotnghiep.nvt.auth;

import org.springframework.stereotype.Component;

import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.dto.ResponseDto;


@Component
public class Auth {
    public ResponseDto permited(String userId){
        ResponseDto responseDto = new ResponseDto();
        // if not permit set respone
        // responseDto.setErrCode(Constants.PERMIT_CODE);
        // responseDto.setErrMsg(Constants.PERMIT_MSG);
        return responseDto;
    }
}
