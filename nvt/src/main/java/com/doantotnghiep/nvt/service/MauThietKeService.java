package com.doantotnghiep.nvt.service;

import java.util.Map;

import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.MauThietKe;

public interface MauThietKeService {
    Map<String,Object> getMtkPaging(RequestDto requestDto);
    ResponseDto createMtk(MauThietKe mauThietKe);
}
