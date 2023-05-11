package com.doantotnghiep.nvt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.doantotnghiep.nvt.model.Branch;

public interface BranchRepository extends JpaRepository<Branch,Long>, JpaSpecificationExecutor<Branch>{
    
    @Query("Select a from Branch a where lower(a.branchName) like lower(concat('%', ?1,'%'))")
    List<Branch> getBranchByName(String name);

    @Query("Select a from Branch a where a.branchCode like ?1%")
    List<Branch> getBranchByCode(String code);

}
