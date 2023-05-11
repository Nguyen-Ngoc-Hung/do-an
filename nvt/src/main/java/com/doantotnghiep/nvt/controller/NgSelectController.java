package com.doantotnghiep.nvt.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doantotnghiep.nvt.auth.Auth;
import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.common.JwtUlti;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.Branch;
import com.doantotnghiep.nvt.model.DonViCP;
import com.doantotnghiep.nvt.model.MauThietKe;
import com.doantotnghiep.nvt.repository.BranchRepository;
import com.doantotnghiep.nvt.repository.DonViCPRepository;
import com.doantotnghiep.nvt.repository.MauThietKeRepository;
import com.doantotnghiep.nvt.service.MauThietKeService;

@RestController
@RequestMapping("api/ngselect")
public class NgSelectController {
    @Autowired
    Auth auth;

    @Autowired
    JwtUlti jwtUlti;

    @Autowired
    MauThietKeRepository mauThietKeRepository;

    @Autowired
    DonViCPRepository donViCPRepository;

    @Autowired
    BranchRepository branchRepository;

    Logger logger = LogManager.getLogger(NgSelectController.class);

    @PostMapping("/branch")
    public ResponseEntity<List<Branch>> getBranch(@RequestBody Branch branch){

        List<Branch> lst = new ArrayList<Branch>();
        if(branch.getBranchCode().equals("")){
            lst = this.branchRepository.findAll();
        }else{
            lst = this.branchRepository.getBranchByCode(branch.getBranchCode());
        }
        return ResponseEntity.ok().body(lst);
    }

    @PostMapping("/mtk")
    public ResponseEntity<List<MauThietKe>> getMtk(@RequestBody MauThietKe mauThietKe){

        List<MauThietKe> lst = new ArrayList<MauThietKe>();
        if(mauThietKe.getMaTk().equals("")){
            lst = this.mauThietKeRepository.findAll();
        }else{
            lst = this.mauThietKeRepository.findByTen(mauThietKe.getMaTk());
        }
        return ResponseEntity.ok().body(lst);
    }

    @PostMapping("/donvicp")
    public ResponseEntity<List<DonViCP>> getDonViCP(@RequestBody DonViCP donViCP){

        List<DonViCP> lst = new ArrayList<DonViCP>();
        if(donViCP.getTen().equals("")){
            lst = this.donViCPRepository.findAll();
        }else{
            lst = this.donViCPRepository.findByTen(donViCP.getTen());
        }
        return ResponseEntity.ok().body(lst);
    }
}
