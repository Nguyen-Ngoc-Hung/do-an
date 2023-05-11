package com.doantotnghiep.nvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.doantotnghiep.nvt.model.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham,Long>, JpaSpecificationExecutor<SanPham>{
    
}
