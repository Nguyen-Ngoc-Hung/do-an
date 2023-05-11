package com.doantotnghiep.nvt.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.ReadExcel;
import com.doantotnghiep.nvt.common.Resource;
import com.doantotnghiep.nvt.common.Ulti;
import com.doantotnghiep.nvt.dto.LoTheDto;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.dto.TheDto;
import com.doantotnghiep.nvt.model.DonViCP;
import com.doantotnghiep.nvt.model.The;
import com.doantotnghiep.nvt.model.TrangThaiThe;
import com.doantotnghiep.nvt.model.Ttsv;
import com.doantotnghiep.nvt.repository.DonViCPRepository;
import com.doantotnghiep.nvt.repository.TheRepository;
import com.doantotnghiep.nvt.repository.TrangThaiTheRepository;
import com.doantotnghiep.nvt.repository.TtsvRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class TheServiceImp implements TheService {

    Logger logger = LogManager.getLogger(TheServiceImp.class);

    @Autowired
    TheRepository theRepository;

    @Autowired
    TtsvRepository ttsvRepository;

    @Autowired
    TrangThaiTheRepository trangThaiTheRepository;

    @Autowired
    DonViCPRepository donViCPRepository;

    @Autowired
    Ulti ulti;

    @Autowired
    Resource resource;
    // public TheServiceImp(TheRepository theRepository){
    // this.theRepository = theRepository;
    // }

    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Map<String, Object> getThePaging(RequestDto requestDto) {
        // TODO lấy danh sách thẻ (có phân trang)
        Map<String, Object> data = new HashMap<>();
        List<The> list = new ArrayList<The>();
        Long total = 0L;
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());

        Specification<The> specification = new Specification<The>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<The> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<The> page = this.theRepository.findAll(specification, pageable);
        // list = page.getContent();
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public Map<String, Object> readFileImport(MultipartFile file) {
        // TODO đọc file excel và import và db
        SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
        Map<String, Object> data = new HashMap<>();
        List<The> listThe = new ArrayList<The>();
        List<The> listTheTonTai = new ArrayList<The>();
        try (InputStream is = file.getInputStream();) {
            XSSFWorkbook workBook = new XSSFWorkbook(is);
            XSSFSheet sheet = workBook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);

                Date ngayBc = DtFormat.parse(ReadExcel.getValue(row.getCell(1)).toString());
                Date ngayPh = DtFormat.parse(ReadExcel.getValue(row.getCell(2)).toString());
                String cif = ReadExcel.getValue(row.getCell(3)).toString();
                String soThe = ReadExcel.getValue(row.getCell(4)).toString();
                String tenKh = ReadExcel.getValue(row.getCell(5)).toString();
                String bdsPh = ReadExcel.getValue(row.getCell(7)).toString();
                String bdsNt = ReadExcel.getValue(row.getCell(8)).toString();
                String am = ReadExcel.getValue(row.getCell(9)).toString();
                String teller = ReadExcel.getValue(row.getCell(10)).toString();
                String suppervisor = ReadExcel.getValue(row.getCell(11)).toString();
                String diaChiNhanThe = ReadExcel.getValue(row.getCell(14)).toString();
                String maSanPham = ReadExcel.getValue(row.getCell(15)).toString();

                var the = The.builder().ngayBc(ngayBc).ngayPh(ngayPh).cif(cif).soThe(soThe).tenKh(tenKh).bdsNt(bdsNt)
                        .bdsPh(bdsPh).am(am).teller(teller).suppervisor(suppervisor).diaChiNhanThe(diaChiNhanThe)
                        .sanPham(maSanPham).build();
                if (checkSoThe(listThe, soThe)) {
                    List<The> temp = this.theRepository.getTheBySoThe(soThe);
                    if (temp.size() != 0) {
                        listTheTonTai.add(the);
                    } else
                        listThe.add(the);
                } else {
                    listTheTonTai.add(the);
                }
            }
            theRepository.saveAll(listThe);
        } catch (Exception e) {
            logger.error(e);
        }
        data.put("valid", listThe);
        data.put("invalid", listTheTonTai);
        return data;
    }

    private Boolean checkSoThe(List<The> listThe, String soThe) {
        for (The the : listThe) {
            if (the.getSoThe().equals(soThe))
                return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getTheSinhVienPaging(RequestDto requestDto) {
        // TODO lấy danh sách thẻ (có phân trang)
        Map<String, Object> data = new HashMap<>();
        List<The> list = new ArrayList<The>();
        Long total = 0L;
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());

        // Specification<The> specification = new Specification<The>() {
        // private static final long serialVersionUID = 1L;

        // @Override
        // public Predicate toPredicate(Root<The> root, CriteriaQuery<?>
        // query,CriteriaBuilder criteriaBuilder) {
        // List<Predicate> predicates = new ArrayList<>();
        // predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("cif"),
        // "686604")));
        // return criteriaBuilder.and(predicates.toArray(new
        // Predicate[predicates.size()]));
        // }
        // };
        // Page<The> page = this.theRepository.getById(specification,pageable);
        Page<Object[]> page = this.theRepository.getTheSV(pageable);
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public Map<String, Object> getLoThe(RequestDto requestDto) {
        // TODO Auto-generated method stub
        Map<String, Object> data = new HashMap<>();
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());

        Page<LoTheDto> page = this.theRepository.getLoThe(pageable);
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public Map<String, Object> countLoThe(Long mtkId, Long trangThai) {
        // TODO Auto-generated method stub
        Map<String, Object> data = new HashMap<>();
        List<Object[]> result = this.theRepository.countThe(mtkId, trangThai);
        data.put("list", result);
        data.put("total", result.size());
        return data;
    }

    @Override
    public ResponseDto exportToFtp(List<Object[]> data, String maLo) {
        // TODO export thong tin in the ra folder tren Sftp
        ResponseDto res = new ResponseDto();
        List<The> listThe = new ArrayList<The>();
        List<Ttsv> listTtsv = new ArrayList<Ttsv>();
        for (Object[] item : data) {
            LinkedHashMap<String, Object> the = (LinkedHashMap<String, Object>) item[0];
            LinkedHashMap<String, Object> ttsv = (LinkedHashMap<String, Object>) item[1];
            Long theId = Long.valueOf(the.get("id").toString());
            Long ttsvId = Long.valueOf(ttsv.get("id").toString());
            Optional<The> theDb = this.theRepository.findById(theId);
            Optional<Ttsv> ttsvDb = this.ttsvRepository.findById(ttsvId);
            if (validate(theDb, ttsvDb)) {
                // set thông tin cho thẻ (mã lô, ngày cập nhật, người cập nhật)
                The theUpdate = theDb.get();
                theUpdate.getTrangThaiThe().setId(5L);
                theUpdate.setMaLo(maLo);
                theUpdate.setNguoiCapnhat("");
                theUpdate.setNgayCapnhat(new Date());
                // set thông tin khi update
                Ttsv ttsvUpdate = ttsvDb.get();
                ttsvUpdate.setTrangThai("4");
                ttsvUpdate.setNguoiCapNhat("");
                ttsvUpdate.setNgayCapNhat(new Date());

                listThe.add(theUpdate);
                listTtsv.add(ttsvUpdate);

            } else {
                res.setErrCode(Constants.FAIL_CODE);
                res.setErrMsg(Constants.FAIL_MSG);
                res.setData(item);
                return res;
            }

        }
        // this.theRepository.saveAll(listThe);
        // this.ttsvRepository.saveAll(listTtsv);
        Map<String, Object> map;
        try (InputStream is = new ClassPathResource("excel-template/TheSinhVienExport.xlsx").getInputStream();) {
            map = new HashMap<String, Object>();
            map.put("listThe", listThe);
            map.put("listTtsv", listTtsv);
            map.put("maLo", maLo);
            map.put("ngayXuat", ulti.currentDateTime());
            // Khởi tạo đường dẫn file trên ftp
            String folder = "/download/QLNVT/PHATHANHTHE";
            LocalDate currentDate = LocalDate.now();
            String path = folder + "/" + "120" + "/" + "matk" + "/" + "sv";
            path = path + "/" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/"
                    + currentDate.getDayOfMonth() + "/" + maLo;
            // Đẩy file excel lên ftp server
            // this.ulti.uploadInputStream(this.resource.createExcelInputStream(is, map),
            // path, maLo);
            // Đẩy ảnh lên ftp server
            res.setData(path);

        } catch (Exception e) {
            logger.error(e);
        }
        return res;

    }

    private boolean validate(Optional<The> theDb, Optional<Ttsv> ttsvDb) {
        if (theDb.isPresent() && ttsvDb.isPresent()) {
            The the = theDb.get();
            Ttsv ttsv = ttsvDb.get();

            if (the.getCif().equals(ttsv.getCif()) && the.getSoThe().equals(ttsv.getSoThe())
                    && (the.getTrangThaiThe().getId() == 2L || the.getTrangThaiThe().getId() == 4L)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Map<String, Object> qlLoThe(RequestDto requestDto) {
        // TODO Auto-generated method stub

        Map<String, Object> data = new HashMap<>();
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());
        Long[] listTrangThai = { 5L, 3L, 11L, 7L };
        Page<LoTheDto> page = this.theRepository.nhanThePaging(listTrangThai, pageable);
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public Map<String, Object> nhanTheTheoLo(RequestDto requestDto) {
        // TODO Auto-generated method stub

        Map<String, Object> data = new HashMap<>();
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());
        Long[] listTrangThai = { 7L, 11L };
        Page<LoTheDto> page = this.theRepository.nhanThePaging(listTrangThai, pageable);
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public Map<String, Object> traTheTheoLo(RequestDto requestDto) {
        // TODO Auto-generated method stub

        Map<String, Object> data = new HashMap<>();
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());
        Long[] listTrangThai = { 13L, 11L };
        Page<LoTheDto> page = this.theRepository.nhanThePaging(listTrangThai, pageable);
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        return data;
    }

    @Override
    public ResponseDto capNhatTheoMaLo(String maLo, Long trangThaiHienTai, Long trangThaiCapNhat) {
        // TODO Cập nhật trạng thái thẻ theo mã lô
        ResponseDto res = new ResponseDto();
        List<The> listUpdate = new ArrayList<The>();
        try {
            listUpdate = this.theRepository.getListUpdate(maLo, trangThaiHienTai);
            TrangThaiThe trangThai = this.trangThaiTheRepository.findById(trangThaiCapNhat).get();
            for (The item : listUpdate) {
                item.setTrangThaiThe(trangThai);
            }
            this.theRepository.saveAll(listUpdate);
            res.setData(listUpdate);
        } catch (Exception e) {
            res.setErrCode(Constants.FAIL_CODE);
            res.setErrMsg("Cập nhật không thành công");
        }
        return res;
    }

    @Override
    public ResponseDto chuyenPhatTheoLo(String maLo, Long trangThaiHienTai, Long trangThaiCapNhat, Long dvcpId) {
        // TODO Auto-generated method stub
        ResponseDto res = new ResponseDto();
        List<The> listUpdate = new ArrayList<The>();
        try {
            listUpdate = this.theRepository.getListUpdate(maLo, trangThaiHienTai);
            TrangThaiThe trangThai = this.trangThaiTheRepository.findById(trangThaiCapNhat).get();
            DonViCP donViCP;
            if (this.donViCPRepository.findById(dvcpId).isPresent()) {
                donViCP = this.donViCPRepository.findById(dvcpId).get();
            } else {
                res.setErrCode(Constants.FAIL_CODE);
                res.setErrMsg("Ma DVCP khong ton tai");
                return res;
            }
            for (The item : listUpdate) {
                item.setTrangThaiThe(trangThai);
                item.setCthDmDvcp(donViCP);
            }
            this.theRepository.saveAll(listUpdate);
            res.setData(listUpdate);
        } catch (Exception e) {
            res.setErrCode(Constants.FAIL_CODE);
            res.setErrMsg("Cập nhật không thành công");
        }
        return res;
    }

    @Override
    public ResponseDto vanTinPaging(RequestDto requestDto, String userId, String role) {
        // TODO Auto-generated method stub
        String userCN = "120";
        ResponseDto responseDto = new ResponseDto();
        Map<String, Object> data = new HashMap<>();
        List<The> list = new ArrayList<The>();
        Long total = 0L;
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() - 1,
                requestDto.getPage().getPageSize());
        ObjectMapper mapper = new ObjectMapper();
        TheDto theDto = mapper.convertValue(requestDto.getBody(), new TypeReference<TheDto>(){});
        // Gson gs = new Gson();
        // System.out.println(requestDto.getBody().toString());
        // final TheDto theDto = gs.fromJson(requestDto.getBody().toString(), TheDto.class);
        Specification<The> specification = new Specification<The>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<The> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (role.equals("CN")) {
                    predicates.add(criteriaBuilder.equal(root.get("bdsNt"), userCN));
                }
                if (theDto.getNgayPhFrom() != null) {
                    try {
                        Date ngayPhFrom = dateFormat.parse(theDto.getNgayPhFrom());
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("ngayPh"), ngayPhFrom));
                    } catch (ParseException e) {
                        responseDto.setErrCode(Constants.FAIL_CODE);
                        responseDto.setErrMsg("Sai dinh dang ngay");
                    }
                }
                if (theDto.getNgayPhTo() != null) {
                    try {
                        Date ngayPhTo = dateFormat.parse(theDto.getNgayPhTo());
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("ngayPh"), ngayPhTo));
                    } catch (ParseException e) {
                        responseDto.setErrCode(Constants.FAIL_CODE);
                        responseDto.setErrMsg("Sai dinh dang ngay dd/mm/yyyy");
                    }
                }
                if(theDto.getDvcp() != null){
                    predicates.add(criteriaBuilder.equal(root.get("cthDmDvcp").get("id"), Long.parseLong(theDto.getDvcp())));
                }
                if(theDto.getCif() != null){
                    predicates.add(criteriaBuilder.like(root.get("cif"), theDto.getCif()));
                }
                if(theDto.getSoThe() != null){
                    predicates.add(criteriaBuilder.like(root.get("soThe"), theDto.getSoThe()));
                }
                if(theDto.getTenKh() != null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("tenKh")), "%" + theDto.getTenKh().toUpperCase() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<The> page = this.theRepository.findAll(specification, pageable);
        // list = page.getContent();
        data.put("list", page.getContent());
        data.put("total", page.getTotalElements());
        responseDto.setData(data);
        return responseDto;
    }

    @Override
    public ResponseDto baoCaoTheChuaNhan(RequestDto requestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'baoCaoTheChuaNhan'");
    }

    @Override
    public ResponseDto baoCaoTheTonKho(RequestDto requestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'baoCaoTheTonKho'");
    }

}
