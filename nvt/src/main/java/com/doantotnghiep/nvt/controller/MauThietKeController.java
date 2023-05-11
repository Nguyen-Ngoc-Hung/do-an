package com.doantotnghiep.nvt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doantotnghiep.nvt.auth.Auth;
import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.JwtUlti;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.MauThietKe;
import com.doantotnghiep.nvt.repository.MauThietKeRepository;
import com.doantotnghiep.nvt.service.MauThietKeService;

@RestController
@RequestMapping("api/mauthietke")
public class MauThietKeController {
    @Autowired
    Auth auth;

    @Autowired
    JwtUlti jwtUlti;

    @Autowired
    MauThietKeRepository mauThietKeRepository;

    @Autowired
    MauThietKeService mauThietKeService;

    Logger logger = LogManager.getLogger(SanPhamController.class);

    @PostMapping(value = {"","/"})
    public ResponseEntity<ResponseDto> getThe(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(mauThietKeService.getMtkPaging(requestDto));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/detail/{id}")
    public ResponseDto chiTietThe(@PathVariable Long id){
        System.out.println("id: " + id);
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
            MauThietKe result = mauThietKeRepository.findById(id).get();
            responseDto.setData(result);
        }
        return responseDto;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createThe(@RequestBody MauThietKe mauThietKe){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = mauThietKeService.createMtk(mauThietKe);
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }
}
