package com.example.demo.models;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

public class dongiaDTO {
	private Date tungay;
    private Date denngay;
    private String mota;
    
    
    
	public Date getTungay() {
		return tungay;
	}
	public void setTungay(Date tungay) {
		this.tungay = tungay;
	}
	public Date getDenngay() {
		return denngay;
	}
	public void setDenngay(Date denngay) {
		this.denngay = denngay;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
    
    
}
