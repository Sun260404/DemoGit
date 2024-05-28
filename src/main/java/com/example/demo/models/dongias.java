package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "dongias")
public class dongias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dongia_id;
    private Date tungay;
    private Date denngay;
    private String mota;
    @OneToMany(mappedBy = "dongia")
    @JsonBackReference
    private List<menudouongs> menudouongs = new ArrayList<>();
    
    
	public int getDongia_id() {
		return dongia_id;
	}
	public void setDongia_id(int dongia_id) {
		this.dongia_id = dongia_id;
	}
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
	public List<menudouongs> getMenudouongs() {
		return menudouongs;
	}
	public void setMenudouongs(List<menudouongs> menudouongs) {
		this.menudouongs = menudouongs;
	}
    
    
}
