package com.example.demo.models;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class hoadonDTO {
	@NotNull (message = "The 'MaBan ID' is required")
	private int maban_id;
	private double tongtien;
	private Date ngayban;
	
	
	public int getMaban_id() {
		return maban_id;
	}
	public void setMaban_id(int maban_id) {
		this.maban_id = maban_id;
	}
	public double getTongtien() {
		return tongtien;
	}
	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}
	public Date getNgayban() {
		return ngayban;
	}
	public void setNgayban(Date ngayban) {
		this.ngayban = ngayban;
	}
	
	
}
