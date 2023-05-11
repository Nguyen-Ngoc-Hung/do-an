package com.doantotnghiep.nvt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.dto.UpdateDto;

public interface TtsvService {
    Map<String,Object> getTtsvPaging(RequestDto requestDto,Long mtkId, String action);
    
    ResponseDto updateTrangThai(UpdateDto updateDto, String action);

    ResponseDto xoaMul(List<Long> listId);

    ResponseDto duyet(List<Long> listId);

    ResponseDto readExcel(MultipartFile file,Long mtkId);
}
