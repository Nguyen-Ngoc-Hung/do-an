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
@Table(name = "THE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class The {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	private String am;

	@Column(name = "BDS_NT")
	private String bdsNt;

	@Column(name = "BDS_PH")
	private String bdsPh;

	private String cif;

	@Column(name = "DIA_CHI_NHAN_THE")
	private String diaChiNhanThe;

	@Column(name = "DICH_VU")
	private String dichVu;

	private String lydo;

	@Column(name = "MA_BILL")
	private String maBill;

	@Column(name = "MA_BILL_HOANCP")
	private String maBillHoancp;

	
	@Column(name = "MA_CB_GIAO_KH")
	private String maCbGiaoKh;

	
	@Column(name = "MA_CB_NHAN_THE")
	private String maCbNhanThe;

	
	@Column(name = "MA_CB_GIAO_THE")
	private String maCbGiaoThe;

	// bi-directional many-to-one association to String
	
	@Column(name = "MA_CB_QLT")
	private String maCbQlt;

	@Column(name = "MA_PHONG_QLT")
	private String maPhongQlt;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_BC")
	private Date ngayBc;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_CAPNHAT")
	private Date ngayCapnhat;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_CB_NHAN_THE")
	private Date ngayCbNhanThe;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_CP")
	private Date ngayCp;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_DU_KIEN")
	private Date ngayDuKien;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_GIAO_KH")
	private Date ngayGiaoKh;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_NHAN")
	private Date ngayNhan;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_PH")
	private Date ngayPh;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TAO")
	private Date ngayTao;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TRA_THE")
	private Date ngayTraThe;

	@Column(name = "NGUOI_CAPNHAT")
	private String nguoiCapnhat;

	@Column(name = "NGUOI_NHAN")
	private String nguoiNhan;

	@Column(name = "NGUOI_TAO")
	private String nguoiTao;

	@Column(name = "SO_THE")
	private String soThe;

	@Column(name = "SO_THE_DAY_DU")
	private String soTheDayDu;

	private String suppervisor;

	private String teller;

	@Column(name = "TEN_KH")
	private String tenKh;

	@Column(name = "TRANG_THAI_CTQLT")
	private String trangThaiCtqlt;

	@Column(name = "VAT_LY")
	private String vatLy;

	// bi-directional many-to-one association to CthDmDv
	@Column(name = "DM_DV_ID")
	private String cthDmDv;

	// bi-directional many-to-one association to CthDmDvcp
	
	// @Column(name = "DM_DVCP_ID")
	@ManyToOne
	@JoinColumn(name = "DM_DVCP_ID")
	private DonViCP cthDmDvcp;

	// bi-directional many-to-one association to CthDmLt
	
	@Column(name = "DM_LT_ID")
	private String loaiThe;

	// bi-directional many-to-one association to LoThe
	
	@Column(name = "MA_LO")
	private String maLo;

	// bi-directional many-to-one association to CthTrangthaiThe
	
	@ManyToOne
	@JoinColumn(name = "TRANG_THAI_THE_ID")
	private TrangThaiThe trangThaiThe;

	
	@Column(name = "SAN_PHAM_ID")
	private String sanPham;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_NHAN_BAN_GIAO")
	private Date ngayNhanBanGiao;

	@Column(name = "DVCP_DICH_VU")
	private String dvcpDichVu;

	@Column(name = "DVCP_MA_BILL")
	private String dvcpMaBill;

	@Column(name = "DVCP_MA_BILL_HOANCP")
	private String dvcpMaBillHoancp;

	@Column(name = "DVCP_NGAY_CHUYEN_PHAT")
	private Date dvcpNgayChuyenPhat;

	@Column(name = "DVCP_NGAY_DU_KIEN")
	private Date dvcpNgayDuKien;

	@Temporal(TemporalType.DATE)
	@Column(name = "DVCP_NGAY_NHAN")
	private Date dvcpNgayNhan;

	@Column(name = "DVCP_NGUOI_NHAN")
	private String dvcpNguoiNhan;

	@Column(name = "GIAY_BIEN_NHAN_THE")
	private String giayBienNhanThe;

	@Column(name = "MA_LO_CP")
	private String maLoCp;

	@Column(name = "TTT_MA_BIEN_BAN")
	private String tttMaBienBan;

	@Column(name = "TT_MA_BIEN_BAN")
	private String ttMaBienBan;

	@Column(name = "BG_QHKH_MA_BIEN_BAN")
	private String bgqhkhMaBienBan;

	@Column(name = "XT_MA_BIEN_BAN")
	private String xtMaBienBan;

	@Column(name = "NLT_MA_BIEN_BAN")
	private String nltMaBienBan;

	@Column(name = "BGT_MA_BIEN_BAN")
	private String bgtMaBienBan;

	@Column(name = "GUI_SMS")
	private String guiSMS;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_NHAN_HOAN_CP")
	private Date ngayNhanHoanCp;

	@Column(name = "NGUOI_NHAN_HOAN_CP")
	private String nguoiNhanHoanCp;

	// SYNC DATA
	@Column(name = "SO_SERIAL")
	private String soSerial;

	@Column(name = "TK_LIEN_KET")
	private String tkLienKet;

	@Column(name = "MA_PHONG_QL")
	private String maPhongQl;

	@Column(name = "MA_KH")
	private String maKh;

	@Column(name = "SO_CMT")
	private String soCmt;

	@Column(name = "DIEN_THOAI")
	private String dienThoai;

	@Column(name = "PAN_TOKEN")
	private String panToken;

}
