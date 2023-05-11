package com.doantotnghiep.nvt.service;

import java.util.Map;

import com.doantotnghiep.nvt.dto.RequestDto;

public interface SanPhamService {
    Map<String,Object> getSanPhamPaging(RequestDto requestDto);
}
