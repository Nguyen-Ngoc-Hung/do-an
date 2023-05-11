package com.doantotnghiep.nvt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doantotnghiep.nvt.auth.Auth;
import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.JwtUlti;
import com.doantotnghiep.nvt.common.Resource;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.dto.UpdateDto;
import com.doantotnghiep.nvt.model.Ttsv;
import com.doantotnghiep.nvt.repository.TtsvRepository;
import com.doantotnghiep.nvt.service.TtsvService;

@RestController
@RequestMapping("api/ttsv")
public class TtsvController {
    @Autowired
    Auth auth;

    @Autowired
    JwtUlti jwtUlti;

    @Autowired
    TtsvRepository ttsvRepository;

    @Autowired
    TtsvService ttsvService;

    @Autowired
    Resource resource;

    Logger logger = LogManager.getLogger(TtsvController.class);

    @PostMapping(value = {"","/"})
    public ResponseEntity<ResponseDto> getTtsvPagging(@RequestBody RequestDto requestDto,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println(mtkId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(ttsvService.getTtsvPaging(requestDto,mtkId,""));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/gdv")
    public ResponseEntity<ResponseDto> gdvGetTtsvPagging(@RequestBody RequestDto requestDto,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println(mtkId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(ttsvService.getTtsvPaging(requestDto,mtkId,"gdv"));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/ksv")
    public ResponseEntity<ResponseDto> ksvGetTtsvPagging(@RequestBody RequestDto requestDto,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println(mtkId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(ttsvService.getTtsvPaging(requestDto,mtkId,"ksv"));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/trangthai/update")
    public ResponseEntity<ResponseDto> capNhatTrangThai(@RequestBody UpdateDto updateDto,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println("Cập nhật trạng thái TTSV");
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = ttsvService.updateTrangThai(updateDto,"");
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/delete-mul")
    public ResponseEntity<ResponseDto> xoaMul(@RequestBody List<Long> listId,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println("Cập nhật trạng thái TTSV");
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = ttsvService.xoaMul(listId);
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/duyet")
    public ResponseEntity<ResponseDto> duyet(@RequestBody List<Long> listId,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println("Cập nhật trạng thái TTSV");
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = ttsvService.duyet(listId);
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
            Ttsv result = ttsvRepository.findById(id).get();
            responseDto.setData(result);
        }
        return responseDto;
    }

    @PostMapping("/uploadttsv/filemau")
    public void fileMauUpload(HttpServletResponse response){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        String fileName = "MauUploadTtsv.xlsx";
        try {
            resource.downloadFileMau( fileName, response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> uploadTtsv(@RequestBody MultipartFile file,@RequestParam(value = "mtkId",required = false) Long mtkId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        System.out.println(mtkId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = ttsvService.readExcel(file,mtkId);
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }
}
