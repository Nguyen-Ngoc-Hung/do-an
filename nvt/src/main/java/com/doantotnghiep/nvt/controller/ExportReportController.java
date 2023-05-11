package com.doantotnghiep.nvt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doantotnghiep.nvt.auth.Auth;
import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.JwtUlti;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.The;
import com.doantotnghiep.nvt.repository.TheRepository;
import com.doantotnghiep.nvt.service.TheService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("api/report")
public class ExportReportController {
    @Autowired
    Auth auth;

    @Autowired
    JwtUlti jwtUlti;

    @Autowired
    TheRepository theRepository;

    @Autowired
    TheService theService;

    Logger logger = LogManager.getLogger(ExportReportController.class);

    @PostMapping("/nhanthe")
    public ResponseEntity<ResponseDto> nhanThe(@RequestParam String maLo, HttpServletResponse response) {
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try {
            // Cập nhật trạng thái thẻ theo mã lô
            responseDto = this.theService.capNhatTheoMaLo(maLo, 7L, 11L);
            List<The> listThe = (List<The>) responseDto.getData();
            int soLuong = listThe.size();
            if (Constants.SUCCESS_CODE.equals(responseDto.getErrCode())) {
                responseDto = this.theService.capNhatTheoMaLo(maLo, 7L, 11L);
                try (InputStream isFileSample = new ClassPathResource("report-template/bien-ban-nhan-the.jrxml")
                        .getInputStream();) {
                    JasperReport jasperReport = JasperCompileManager
                            .compileReport(isFileSample);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    URL logo = this.getClass().getClassLoader().getResource("images/logo.png");
                    parameters.put("benGiao", "Viettel Post");
                    parameters.put("hoTenCbGiao", "Nguyễn Ngọc Hưng");
                    parameters.put("benNhan", "CN Cầu Giấy");
                    parameters.put("hoTenCbNhan", "Nguyễn Hưng Ngọc");
                    parameters.put("phongNhan", "Phòng nhân sự");
                    parameters.put("maLo", "SV_123982439872");
                    parameters.put("chiNhanh", "Duy Tân");
                    parameters.put("soLuong", soLuong);
                    parameters.put("logo", logo);

                    // compile file report cùng các tham số đã khai báo
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                            new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    logger.error(e);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/trathe")
    public ResponseEntity<ResponseDto> traThe(@RequestParam String maLo, HttpServletResponse response) {
        String userId = jwtUlti.getUserNameFromCookie();
        ResponseDto responseDto = new ResponseDto();
        responseDto = auth.permited(userId);
        try {
            // Cập nhật trạng thái thẻ theo mã lô
            responseDto = this.theService.capNhatTheoMaLo(maLo, 11L, 13L);
            List<The> listThe = (List<The>) responseDto.getData();
            int soLuong = listThe.size();
            if (Constants.SUCCESS_CODE.equals(responseDto.getErrCode())) {
                responseDto = this.theService.capNhatTheoMaLo(maLo, 11L, 13L);
                try (InputStream isFileSample = new ClassPathResource("report-template/bien-ban-ban-tra-the.jrxml")
                        .getInputStream();) {
                    JasperReport jasperReport = JasperCompileManager
                            .compileReport(isFileSample);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    URL logo = this.getClass().getClassLoader().getResource("images/logo.png");
                    parameters.put("truong", "ĐH Công nghiệp Hà Nội");
                    parameters.put("hoTenCbGiao", "Nguyễn Ngọc Hưng");
                    parameters.put("benNhan", "CN Cầu Giấy");
                    parameters.put("hoTenCbNhan", "Nguyễn Hưng Ngọc");
                    parameters.put("phongNhan", "Phòng nhân sự");
                    parameters.put("maLo", "SV_123982439872");
                    parameters.put("chiNhanh", "Duy Tân");
                    parameters.put("soLuong", soLuong);
                    parameters.put("logo", logo);
                    JRBeanCollectionDataSource listJR = new JRBeanCollectionDataSource(listThe);
                    parameters.put("datasource", listJR);

                    // compile file report cùng các tham số đã khai báo
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                            new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    logger.error(e);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return ResponseEntity.ok().body(responseDto);
    }

}
