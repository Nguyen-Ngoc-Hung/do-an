package com.doantotnghiep.nvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.doantotnghiep.nvt.model.TrangThaiThe;

public interface TrangThaiTheRepository extends JpaRepository<TrangThaiThe,Long>, JpaSpecificationExecutor<TrangThaiThe> {
    
}
