package com.doantotnghiep.nvt.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.doantotnghiep.nvt.dto.RequestDto;


public interface TrangThaiTheService {
    Map<String,Object> getTrangThaiThePaging(RequestDto requestDto);
}
