package com.doantotnghiep.nvt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="DM_SAN_PHAM")
@Getter
@Setter
public class SanPham {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "TEN_SAN_PHAM")
	private String tenSanPham;

	@Column(name = "MA_SAN_PHAM")
	private String maSanPham;

	@Column(name = "NGUOI_TAO")
	private String nguoiTao;

	@Column(name = "NGAY_TAO")
	private Date ngayTao;

	@Column(name = "NGUOI_CAPNHAT")
	private String nguoiCapNhat;

	@Column(name = "NGAY_CAPNHAT")
	private Date ngayCapNhat;

}
