package com.doantotnghiep.nvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.doantotnghiep.nvt.model.Ttsv;

public interface TtsvRepository extends JpaRepository<Ttsv,Long>, JpaSpecificationExecutor<Ttsv> {
    @Query("Select count(a) from Ttsv a where a.soThe = ?1 and a.cif = ?2 and a.trangThaiDuLieu = 0")
    Long validateTtsv(String soThe,String cif);
}
