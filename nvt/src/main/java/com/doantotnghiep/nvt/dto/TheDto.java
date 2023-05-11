package com.doantotnghiep.nvt.dto;

import java.util.Date;

import com.doantotnghiep.nvt.model.Branch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheDto {
    Long id;
    String cif;
    String soThe;
    Branch bdsNt;
    Branch bdsPh;
    String maLo;
    String tenKh;
    String ngayPh;
    String ngayPhFrom;
    String ngayPhTo;
    String dvcp;
    
}
