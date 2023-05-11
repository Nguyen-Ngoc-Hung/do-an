package com.doantotnghiep.nvt.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.ReadExcel;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.dto.UpdateDto;
import com.doantotnghiep.nvt.model.MauThietKe;
import com.doantotnghiep.nvt.model.The;
import com.doantotnghiep.nvt.model.TrangThaiThe;
import com.doantotnghiep.nvt.model.Ttsv;
import com.doantotnghiep.nvt.repository.TheRepository;
import com.doantotnghiep.nvt.repository.TtsvRepository;
import com.google.gson.Gson;

@Service
public class TtsvServiceImp implements TtsvService {

    @Autowired
    TtsvRepository ttsvRepository;

    @Autowired
    TheRepository theRepository;

    Logger logger = LogManager.getLogger(TtsvServiceImp.class);

    @Override
    public Map<String, Object> getTtsvPaging(RequestDto requestDto, Long mtkId, String action) {
        Map<String, Object> data = new HashMap<>();
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());

        Specification<Ttsv> specification = new Specification<Ttsv>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Ttsv> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (mtkId != null) {
                    predicates.add(criteriaBuilder
                            .and(criteriaBuilder.equal(root.get("mtk").get("id"), mtkId)));
                }

                if (action.equals("gdv")) {
                    System.out.println("Màn hình giao dịch viên");
                    predicates.add(criteriaBuilder
                            .and(root.get("trangThai").in("0", "1", "3", "5")));
                }

                if (action.equals("ksv")) {
                    System.out.println("Màn hình KSV");
                    predicates.add(criteriaBuilder
                            .and(root.get("trangThai").in("1", "2")));
                            predicates.add(criteriaBuilder
                                    .and(root.get("trangThaiDuLieu").in("0")));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<Ttsv> page = this.ttsvRepository.findAll(specification, pageable);
        // list = page.getContent();
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public ResponseDto updateTrangThai(UpdateDto updateDto, String action) {
        // TODO Cập nhật trạng thái TTSV (theo listId)
        Map<String, Object> data = new HashMap<>();
        List<Ttsv> listUpdate = new ArrayList<Ttsv>();
        ResponseDto response = new ResponseDto();
        // System.out.println(requestDto.getBody());
        // Gson gs = new Gson();
        // UpdateDto updateDto = (UpdateDto)
        // gs.fromJson(requestDto.getBody().toString(), UpdateDto.class);
        // System.out.println(updateDto.getListId());
        if (trangThaiHopLe(updateDto.getTrangThai())) {
            String trangThaiUpdate = updateDto.getTrangThai();
            String lydo = updateDto.getLyDo();
            for (int i = 0; i < updateDto.getListId().length; ++i) {
                listUpdate.add(this.ttsvRepository.findById(Long.parseLong(updateDto.getListId()[i])).get());
            }
            listUpdate.parallelStream()
                    .forEach(ttsv -> {
                        ttsv.setTrangThai(trangThaiUpdate);
                        ttsv.setLyDo(lydo);
                    });
            this.ttsvRepository.saveAll(listUpdate);
        } else {
            response.setErrCode(Constants.FAIL_CODE);
            response.setErrMsg("Trạng thái không hợp lệ");
        }

        data.put("action", "Thành công");
        return response;
    }

    public boolean trangThaiHopLe(String trangThai) {
        String[] trangThaiArr = { "1", "2", "3", "4", "5" };
        for (int i = 0; i < trangThaiArr.length; ++i) {
            if (trangThai.equals(trangThaiArr[i]))
                return true;
        }
        return false;
    }

    @Override
    public ResponseDto readExcel(MultipartFile file, Long mtkId) {
        // TODO đọc file excel thông tin sinh viên -> import vào db
        SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
        var mtk = MauThietKe.builder().id(mtkId).build();
        List<Ttsv> listAdd = new ArrayList<Ttsv>();
        List<Ttsv> listError = new ArrayList<Ttsv>();
        List<Ttsv> listTheSai = new ArrayList<Ttsv>();
        List<Ttsv> listSaiBds = new ArrayList<Ttsv>();
        List<Ttsv> listDaTonTai = new ArrayList<Ttsv>();
        ResponseDto responseDto = new ResponseDto();
        try (InputStream is = file.getInputStream()) {
            XSSFWorkbook workBook = new XSSFWorkbook(is);
            XSSFSheet sheet = workBook.getSheetAt(0);
            for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                String cif = ReadExcel.getValue(row.getCell(1)).toString();
                String soThe = ReadExcel.getValue(row.getCell(2)).toString();
                String tenSv = ReadExcel.getValue(row.getCell(3)).toString();
                String gioiTinh = ReadExcel.getValue(row.getCell(4)).toString();
                String maSv = ReadExcel.getValue(row.getCell(5)).toString();
                Date ngaySinh = DtFormat.parse(ReadExcel.getValue(row.getCell(6)).toString());
                String tenAnh = ReadExcel.getValue(row.getCell(7)).toString();
                var ttsv = Ttsv.builder().mtk(mtk).cif(cif).soThe(soThe).tenSv(tenSv).gioiTinh(gioiTinh).maSv(maSv)
                        .ngaySinh(ngaySinh).tenAnh(tenAnh).trangThai("0").build();

                String trangThaiDuLieu = validateTtsv(listAdd, ttsv);

                ttsv.setTrangThaiDuLieu(trangThaiDuLieu);
                listAdd.add(ttsv);
            }
            for (Ttsv item : listAdd) {
                if (item.getTrangThaiDuLieu().equals("1")) {
                    listTheSai.add(item);
                } else if (item.getTrangThaiDuLieu().equals("2")) {
                    listSaiBds.add(item);
                } else if (item.getTrangThaiDuLieu().equals("3")) {
                    listDaTonTai.add(item);
                }
            }

            this.ttsvRepository.saveAll(listAdd);
        } catch (Exception e) {
            responseDto.setErrCode(Constants.FAIL_CODE);
            responseDto.setErrCode("Upload không thành công, kiểm tra lại file upload");
            logger.error(e);
        }
        return responseDto;
    }

    private String validateTtsv(List<Ttsv> lst, Ttsv ttsv) {

        // Kiểm tra dòng hiện tại với các dòng đã xét
        for (Ttsv item : lst) {
            if (item.getCif().equals(ttsv.getCif()) && item.getSoThe().equals(ttsv.getSoThe())) {
                if (item.getTrangThaiDuLieu().equals("0")) {
                    return "3";// Thông tin sinh viên đã tồn tại
                } else {
                    return item.getTrangThaiDuLieu();
                }
            }
        }

        if (this.ttsvRepository.validateTtsv(ttsv.getSoThe(), ttsv.getCif()) > 0) {
            return "3";
        }

        System.out.println(this.theRepository.validateTtsv(ttsv.getSoThe(), ttsv.getCif(), "120"));
        // Kiểm tra dữ thông tin sinh viên với dữ liệu trong bảng thẻ
        if (this.theRepository.validateTtsv(ttsv.getSoThe(), ttsv.getCif(), "120") == 1) {
            System.out.println("Thông tin sinh viên đúng");
            return "0";
        } else {
            if (this.theRepository.validateTtsv2(ttsv.getSoThe(), ttsv.getCif()) == 1) {
                return "2";
            } else {
                return "1";
            }
        }
    }

    @Override
    public ResponseDto xoaMul(List<Long> listId) {
        // TODO Xoá thông tin sinh viên theo listId
        ResponseDto responseDto = new ResponseDto();
        try {
            this.ttsvRepository.deleteAllById(listId);
        } catch (Exception e) {
            logger.error(e);
            responseDto.setErrCode(Constants.FAIL_CODE);
            responseDto.setErrCode("Xoá không thành công");
        }
        return responseDto;
    }

    @Override
    public ResponseDto duyet(List<Long> listId) {
        // TODO Duyệt thông tin sinh viên theo list id
        ResponseDto responseDto = new ResponseDto();
        // Chuyển trạng thái thông tin sinh viên -> ksv duyệt (3)
        List<Ttsv> listTtsvUpdate = new ArrayList<Ttsv>();
        List<The> listTheUpdate = new ArrayList<The>();
        for (int i = 0; i < listId.size(); ++i) {
            Ttsv ttsv = this.ttsvRepository.findById(listId.get(i)).get();
            if (ttsv != null && ttsv.getTrangThai().equals("1")) {
                ttsv.setTrangThai("2");
                ttsv.setNgayCapNhat(new Date());
                listTtsvUpdate.add(ttsv);
            } else {
                responseDto.setErrCode(Constants.FAIL_CODE);
                responseDto.setErrMsg("Loi ttsvId: " + listId.get(i));
                return responseDto;
            }
        }
        TrangThaiThe guiTsc = TrangThaiThe.builder().id(2L).build();
        for (Ttsv item : listTtsvUpdate) {
            The the = this.theRepository.getBySoTheAndCif(item.getSoThe(), item.getCif());
            if (the != null && (the.getTrangThaiThe().getId() == 1L || the.getTrangThaiThe().getId() == 4L)) {
                the.setTrangThaiThe(guiTsc);
                the.setNgayCapnhat(new Date());
                the.setNguoiCapnhat("hungnn");
                listTheUpdate.add(the);
            }else{
                responseDto.setErrCode(Constants.FAIL_CODE);
                responseDto.setErrMsg("Loi ttsvId: " + item.getId());
                return responseDto;
            }

        }

        // this.theRepository.saveAll(listTheUpdate);
        // this.ttsvRepository.saveAll(listTtsvUpdate);
        save(listTheUpdate, listTtsvUpdate);

        return responseDto;
    }

    @Transactional
    private void save(List<The> listThe, List<Ttsv> listTtsv) {
        this.theRepository.saveAll(listThe);
        this.ttsvRepository.saveAll(listTtsv);
    }
}
