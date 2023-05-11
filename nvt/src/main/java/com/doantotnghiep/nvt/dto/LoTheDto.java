package com.doantotnghiep.nvt.dto;

import com.doantotnghiep.nvt.model.DonViCP;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoTheDto {
    String maLo;
    Long soLuong;
    Long trangThaiTheId;
    DonViCP donViCP;
    public LoTheDto(){
        maLo = "";  
        soLuong = 0L;
        trangThaiTheId = 0L;
        donViCP = new DonViCP();
    }
    public LoTheDto(String maLo, Long soLuong,Long trangThaiTheId,DonViCP donViCP){
        this.maLo = maLo;
        this.soLuong = soLuong;
        this.trangThaiTheId = trangThaiTheId;
        this.donViCP = donViCP;
    }

    public LoTheDto(String maLo,String soLuong, String trangThaiTheId,DonViCP donViCP){
        this.maLo = maLo;
        this.soLuong = Long.parseLong(soLuong);
        this.trangThaiTheId = Long.parseLong(trangThaiTheId);
        this.donViCP = donViCP;
    }
}
