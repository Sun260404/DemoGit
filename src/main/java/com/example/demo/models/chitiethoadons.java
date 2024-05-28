package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chitiethoadons")
public class chitiethoadons {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int machitiet_id;
	private double dongia;
	private double souong;
	@ManyToOne
	@JoinColumn(name = "mahoadon_id", referencedColumnName = "mahoadon_id")
	private hoadons hoadon;
	
	@ManyToOne
	@JoinColumn(name = "madouong_id", referencedColumnName = "madouong_id")
	private menudouongs menudouong;
	
	

	public int getMachitiet_id() {
		return machitiet_id;
	}

	public void setMachitiet_id(int machitiet_id) {
		this.machitiet_id = machitiet_id;
	}

	public double getDongia() {
		return dongia;
	}

	public void setDongia(double dongia) {
		this.dongia = dongia;
	}

	public double getSouong() {
		return souong;
	}

	public void setSouong(double souong) {
		this.souong = souong;
	}

	public hoadons getHoadon() {
		return hoadon;
	}

	public void setHoadon(hoadons hoadon) {
		this.hoadon = hoadon;
	}

	public menudouongs getMenudouong() {
		return menudouong;
	}

	public void setMenudouong(menudouongs menudouong) {
		this.menudouong = menudouong;
	}
	
	
}
