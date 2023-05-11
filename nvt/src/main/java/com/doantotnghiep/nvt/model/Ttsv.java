package com.doantotnghiep.nvt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TTSV")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ttsv {
    
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "MTK_ID")
    private MauThietKe mtk;

    @Column(name = "CIF")
    private String cif;

    @Column(name = "SO_THE")
    private String soThe;

    @Column(name = "TEN_SV")
    private String tenSv;

    @Column(name = "GIOI_TINH")
    private String gioiTinh;

    @Column(name = "MA_SV")
    private String maSv;

    @Column(name = "NGAY_SINH")
    private Date ngaySinh;

    @Column(name = "TEN_ANH")
    private String tenAnh;

    @Column(name = "PATH_ANH")
    private String pathAnh;

    @Column(name = "TRANG_THAI")
    private String trangThai;

    @Column(name = "LY_DO")
    private String lyDo;

    @Column(name = "TRANG_THAI_DU_LIEU")
    private String trangThaiDuLieu;

    @Column(name = "NGUOI_TAO")
    private String nguoiTao;

    @Column(name = "NGUOI_CAP_NHAT")
    private String nguoiCapNhat;

    @Column(name = "NGAY_TAO")
    private Date ngayTao;

    @Column(name = "NGAY_CAP_NHAT")
    private Date ngayCapNhat;

}
