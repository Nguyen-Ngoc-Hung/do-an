package com.doantotnghiep.nvt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.The;

public interface TheService {
    Map<String,Object> getThePaging(RequestDto requestDto);

    Map<String,Object> readFileImport(MultipartFile file);

    Map<String,Object> getTheSinhVienPaging(RequestDto requestDto);

    Map<String,Object> getLoThe(RequestDto requestDto);

    Map<String,Object> nhanTheTheoLo(RequestDto requestDto);

    Map<String,Object> qlLoThe(RequestDto requestDto);

    Map<String,Object> traTheTheoLo(RequestDto requestDto);

    Map<String,Object> countLoThe(Long mtkId,Long trangThai);

    ResponseDto exportToFtp(List<Object[]> data,String malo);

    ResponseDto capNhatTheoMaLo(String maLo,Long trangThaiHienTai, Long trangThaiCapNhat);

    ResponseDto chuyenPhatTheoLo(String maLo,Long trangThaiHienTai, Long trangThaiCapNhat, Long dvcpId);

    ResponseDto vanTinPaging(RequestDto requestDto,String userId , String role);
    
    ResponseDto baoCaoTheChuaNhan(RequestDto requestDto);

    ResponseDto baoCaoTheTonKho(RequestDto requestDto);


}
