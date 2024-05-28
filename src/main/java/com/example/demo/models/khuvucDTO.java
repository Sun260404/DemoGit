package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class khuvucDTO {
	@NotEmpty (message = "The 'TenKhuVuc' is required")
	private String tenkhuvuc;
	private String mota;
	
	
	public String getTenkhuvuc() {
		return tenkhuvuc;
	}
	public void setTenkhuvuc(String tenkhuvuc) {
		this.tenkhuvuc = tenkhuvuc;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	
	
}
