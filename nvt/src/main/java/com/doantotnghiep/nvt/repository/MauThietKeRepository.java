package com.doantotnghiep.nvt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.doantotnghiep.nvt.model.MauThietKe;

public interface MauThietKeRepository extends JpaRepository<MauThietKe,Long>, JpaSpecificationExecutor<MauThietKe> {
    
    @Query("Select a from MauThietKe a where lower(a.maTk) like lower(concat('%',?1,'%'))")
    List<MauThietKe> findByTen(String maTk);

}
