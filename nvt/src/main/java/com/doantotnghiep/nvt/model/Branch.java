package com.doantotnghiep.nvt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BRANCHES")
@Getter
@Setter
public class Branch {
    @Id
    @Column(name = "BRANCH_ID", nullable = false)
    private String branchCode;

    @Column(name = "BRANCH_NM")
    private String branchName;

    @Column(name = "ADDR1")
    private String addr1;

    @Column(name = "ADDR2")
    private String addr2;

    @Column(name = "BRANCH_NV")
    private String branchNv;

    @Column(name = "MAIN_ID")
    private String mainId;
}
