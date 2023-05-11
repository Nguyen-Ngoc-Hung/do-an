package com.doantotnghiep.nvt.service;

import java.util.Map;

import com.doantotnghiep.nvt.dto.RequestDto;

public interface BranchService {
    Map<String,Object> getBranchPaging(RequestDto requestDto);
}
