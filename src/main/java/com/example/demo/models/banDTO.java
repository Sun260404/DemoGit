package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class banDTO {
	@NotEmpty (message = "The 'TenBan' is required")
	private String tenban;
	private String trangthai;
	private String mota;
	@NotNull (message = "The 'MaKhuVuc_ID' is required")
	private int makhuvuc_id;
	
	
	public String getTenban() {
		return tenban;
	}
	public void setTenban(String tenban) {
		this.tenban = tenban;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public int getMakhuvuc_id() {
		return makhuvuc_id;
	}
	public void setMakhuvuc_id(int makhuvuc_id) {
		this.makhuvuc_id = makhuvuc_id;
	}
	
	
}
