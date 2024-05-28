package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class menudouongDTO {
	@NotEmpty (message = "The 'TenDoUong' is required")
	private String tendouong;
	
	@NotNull (message = "The 'DonGia_ID' is required")
	private int dongia_id;
	
	@NotNull (message = "The 'MaDanhMuc_ID' is required")
	private int madanhmuc_id;

	
	
	public String getTendouong() {
		return tendouong;
	}

	public void setTendouong(String tendouong) {
		this.tendouong = tendouong;
	}

	public int getDongia_id() {
		return dongia_id;
	}

	public void setDongia_id(int dongia_id) {
		this.dongia_id = dongia_id;
	}

	public int getMadanhmuc_id() {
		return madanhmuc_id;
	}

	public void setMadanhmuc_id(int madanhmuc_id) {
		this.madanhmuc_id = madanhmuc_id;
	}
	
	
}
