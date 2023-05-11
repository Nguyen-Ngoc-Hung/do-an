package com.doantotnghiep.nvt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.doantotnghiep.nvt.model.The;
import com.doantotnghiep.nvt.repository.TheRepository;
import com.doantotnghiep.nvt.service.TheService;


@RestController
@RequestMapping("api/the")
public class TheController {
    
    @Autowired
    Auth auth;

    @Autowired
    JwtUlti jwtUlti;

    @Autowired
    TheRepository theRepository;

    @Autowired
    TheService theService;

    @Autowired
    Resource resource;

    Logger logger = LogManager.getLogger(TheController.class);


    @PostMapping(value = {"","/"})
    public ResponseEntity<ResponseDto> getThe(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(theService.getThePaging(requestDto));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/detail/{id}")
    public ResponseEntity<ResponseDto> chiTietThe(@PathVariable Long id){
        System.out.println("id: " + id);
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
            The result = theRepository.findById(id).get();
            responseDto.setData(result);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/uploadthe/filemau")
    public void fileMauUpload(HttpServletResponse response){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        String fileName = "MauUploadThe.xlsx";
        try {
            resource.downloadFileMau( fileName, response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e);
        }
    }

    @PostMapping("/uploadthe")
    public ResponseEntity<ResponseDto> uploadThe(@RequestBody MultipartFile file){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData( this.theService.readFileImport(file));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/thesinhvien")
    public ResponseEntity<ResponseDto> getTheSV(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.getTheSinhVienPaging(requestDto));
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/lothe")
    public ResponseEntity<ResponseDto> getLoThe(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.getLoThe(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }
// count thẻ theo mtkId và trangThaiTheId
    @PostMapping("/count")
    public ResponseEntity<ResponseDto> countTheByMtk(@RequestParam Long mtkId,@RequestParam Long trangThaiTheId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.countLoThe(mtkId,trangThaiTheId));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/thesinhvien/export")
    public ResponseEntity<ResponseDto> exportToFtp(@RequestBody List<Object[]> data,@RequestParam String maLo){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.exportToFtp(data,maLo);
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/quanlylothe-paging")
    public ResponseEntity<ResponseDto> quanLyLoThe(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.qlLoThe(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/dasanxuat")
    public ResponseEntity<ResponseDto> daSanXuat(@RequestParam String maLo){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.capNhatTheoMaLo(maLo, 5L, 3L);
                // responseDto.setData(this.theService.nhanTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/chuyenphat")
    public ResponseEntity<ResponseDto> chuyenphat(@RequestParam String maLo,@RequestParam Long dvcpId){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.chuyenPhatTheoLo(maLo, 3L, 7L,dvcpId);
                // responseDto.setData(this.theService.nhanTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/nhanthe-paging")
    public ResponseEntity<ResponseDto> nhanThePaging(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.nhanTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/nhanthe")
    public ResponseEntity<ResponseDto> nhanThe(@RequestParam String maLo){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.capNhatTheoMaLo(maLo, 7L, 11L);
                // responseDto.setData(this.theService.nhanTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/trathe-paging")
    public ResponseEntity<ResponseDto> traThePaging(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto.setData(this.theService.traTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping("/trathe")
    public ResponseEntity<ResponseDto> traThe(@RequestParam String maLo){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                // Cập nhật trạng thái thẻ từ CN đã nhận => KH đã nhận theo mã lô
                responseDto = this.theService.capNhatTheoMaLo(maLo, 11L, 13L);
                // responseDto.setData(this.theService.nhanTheTheoLo(requestDto));
            }
        }catch(Exception e){  
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto); 
    }

    @PostMapping(value = "/vantin")
    public ResponseEntity<ResponseDto> vanTinPaging(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.vanTinPaging(requestDto,userId , "");
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping(value = "/chinhanh/vantin")
    public ResponseEntity<ResponseDto> vanTinCNPaging(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.vanTinPaging(requestDto,userId , "CN");
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping(value = "/baocao/thechuanhan")
    public ResponseEntity<ResponseDto> theChuaNhan(@RequestBody RequestDto requestDto){
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try{
            if(Constants.SUCCESS_CODE.equals(responseDto.getErrCode())){
                responseDto = this.theService.vanTinPaging(requestDto, userId, "");
            }
        }catch(Exception e){
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }


}
