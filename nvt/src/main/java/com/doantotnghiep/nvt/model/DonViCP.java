package com.doantotnghiep.nvt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DM_DVCP")
public class DonViCP {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "DIACHI")
	private String diachi;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_CAPNHAT")
	private Date ngayCapnhat;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TAO")
	private Date ngayTao;

	@Column(name = "NGUOI_CAPNHAT")
	private String nguoiCapnhat;

	@Column(name = "NGUOI_TAO")
	private String nguoiTao;

    @Column(name = "TEN")
	private String ten;

	@Column(name = "TRANG_THAI")
	private String trangThai;
}
