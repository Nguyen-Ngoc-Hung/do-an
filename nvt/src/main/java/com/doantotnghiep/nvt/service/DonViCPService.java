package com.doantotnghiep.nvt.service;

import java.util.Map;

import com.doantotnghiep.nvt.dto.RequestDto;

public interface DonViCPService {
    Map<String,Object> getDvcpPaging(RequestDto requestDto);
}
