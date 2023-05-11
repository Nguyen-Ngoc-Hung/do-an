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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MAU_THIET_KE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MauThietKe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "MA_TK", unique = true)
    private String maTk;

    @Temporal(TemporalType.DATE)
    @Column(name = "NGAY_KBTK")
    private Date ngayKbtk;

    @Column(name = "TEN_TRUONG")
    private String tenTruong;

    @Column(name = "TRANG_THAI")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "BDS")
    private Branch branch;
}
