package com.doantotnghiep.nvt.dto;

import java.util.ArrayList;

import com.doantotnghiep.nvt.common.Constants;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseDto {

    private String errCode;
    private String errMsg;
    private Object data;

    public ResponseDto(String errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }
    public ResponseDto(){
        this.errCode = Constants.SUCCESS_CODE;
        this.errMsg = Constants.SUCCESS_MSG;
        this.data = new ArrayList<>();
    }
}

