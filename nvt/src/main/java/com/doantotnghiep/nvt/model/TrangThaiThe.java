package com.doantotnghiep.nvt.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="TRANG_THAI_THE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrangThaiThe {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "GIA_TRI")
	private String giaTri;

	@Column(name = "MOTA")
	private String moTa;

	@Column(name = "TRANG_THAI")
	private String trangThai;

	@Column(name = "NGUOI_TAO")
	private String nguoiTao;

	@Column(name = "NGAY_TAO")
	private Date ngayTao;

	@Column(name = "NGUOI_CAPNHAT")
	private String nguoiCapNhat;

	@Column(name = "NGAY_CAPNHAT")
	private Date ngayCapNhat;

	@JsonIgnore
	@OneToMany(mappedBy = "trangThaiThe")
	private Set<The> thes;
}
