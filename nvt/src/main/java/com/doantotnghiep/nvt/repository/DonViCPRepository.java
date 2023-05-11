package com.doantotnghiep.nvt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.doantotnghiep.nvt.model.DonViCP;

public interface DonViCPRepository extends JpaRepository<DonViCP,Long>, JpaSpecificationExecutor<DonViCP> {
    

    @Query("Select a from DonViCP a where lower(a.ten) like lower(concat('%',?1,'%'))")
    List<DonViCP> findByTen(String maTk);
}
