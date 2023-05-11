package com.doantotnghiep.nvt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.doantotnghiep.nvt.dto.LoTheDto;
import com.doantotnghiep.nvt.model.The;


public interface TheRepository extends JpaRepository<The,Long>, JpaSpecificationExecutor<The>{
    
    @Query("Select a from The a where a.soThe = ?1")
    List<The> getTheBySoThe(String soThe);

    @Query("Select a from The a where a.soThe = ?1 and a.cif = ?2")
    The getBySoTheAndCif(String soThe,String cif);
    
    @Query("Select count(a) from The a where a.soThe = ?1 and a.cif = ?2 and a.bdsPh = ?3")
    Long validateTtsv(String soThe,String cif, String bds);

    @Query("Select count(a) from The a where a.soThe = ?1 and a.cif = ?2 ")
    Long validateTtsv2(String soThe,String cif);

    // @Query("Select a,b,c from CthThe a, CthTtsv b JOIN b.cthMtk c where a.soThe = b.soThe and a.cif = b.cif and a.loaiThe.id = ?1 AND"
	// 		+ " ((?2=0L AND (a.trangThaiQlt.id=2 OR a.trangThaiQlt.id=3 OR a.trangThaiQlt.id=4 OR a.trangThaiQlt.id=300)) OR (?2 != 0 AND a.trangThaiQlt.id=?2)) AND"
	// 		+ " (?3 is null OR a.bdsPh = ?3) AND"
	// 		+ " (?4 = 0L OR b.cthMtk.id = ?4) AND"
	// 		+ " (?5 = 0 OR a.cthDmDv.id = ?5) AND"
	// 		+ " (?6 is null OR UPPER(b.cthMtk.tenTruong) like UPPER(concat('%',?6,'%'))) AND"
	// 		+ " (?7 is null OR a.ngayBc >= TO_DATE(?7, 'DD-MM-YYYY')) AND"
	// 		+ " (?8 is null OR a.ngayBc <= TO_DATE(?8, 'DD-MM-YYYY')) AND (b.trangThai=3 or b.trangThai = 4 or b.trangThai = 5)")
	// Page<Object[]> layThongTinSV(Long loaithe, Long ttt, String bdsId, Long maTk, Integer dvcthId, String truong,
	// 		String fromDate, String toDate, Pageable pageable);

    @Query("SELECT a,b from The a INNER JOIN Ttsv b ON a.cif = b.cif AND a.soThe = b.soThe where a.trangThaiThe = 2 and b.trangThaiDuLieu = 0")
    Page<Object[]> getTheSV(Pageable pageable);

    @Query("SELECT new com.doantotnghiep.nvt.dto.LoTheDto(a.maLo,count(a),a.trangThaiThe.id,a.cthDmDvcp) from The a where a.maLo is not null group by a.maLo,a.trangThaiThe.id,a.cthDmDvcp") 
    Page<LoTheDto> getLoThe(Pageable pageable);

    @Query("SELECT new com.doantotnghiep.nvt.dto.LoTheDto(a.maLo,count(a),a.trangThaiThe.id,a.cthDmDvcp) from The a where a.trangThaiThe.id IN ?1 group by a.maLo,a.trangThaiThe.id,a.cthDmDvcp") 
    Page<LoTheDto> nhanThePaging(Long[] listTrangThai,Pageable pageable);

    @Query("SELECT a,b from The a INNER JOIN Ttsv b ON a.cif = b.cif AND a.soThe = b.soThe where a.trangThaiThe.id = ?2 and b.trangThaiDuLieu = 0 and b.mtk.id = ?1")
    List<Object[]> countThe(Long maTkId, Long trangThaiTheId);

    @Query("SELECT a from The a where a.maLo = ?1 and a.trangThaiThe.id = ?2")
    List<The> getListUpdate(String maLo, Long trangThaiTheId);

    // Page<The> getById(Specification<The> specification,Pageable pageable);
}
