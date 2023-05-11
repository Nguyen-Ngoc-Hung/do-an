package com.doantotnghiep.nvt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {
    String listId[];
    String trangThai;
    String lyDo;
    public UpdateDto(){
        listId = new String[0];
        lyDo = "";
        trangThai = "0";
    }
}
