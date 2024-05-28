package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hoadons")
public class hoadons {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int mahoadon_id;

@ManyToOne
@JoinColumn(name = "maban_id", referencedColumnName = "maban_id")
private bans ban;

@OneToMany(mappedBy = "hoadon")
@JsonBackReference
private List<chitiethoadons> chitiethoadons = new ArrayList<>();

private double tongtien;
public Date ngayban;


public int getMahoadon_id() {
	return mahoadon_id;
}
public void setMahoadon_id(int mahoadon_id) {
	this.mahoadon_id = mahoadon_id;
}
public bans getBan() {
	return ban;
}
public void setBan(bans ban) {
	this.ban = ban;
}
public List<chitiethoadons> getChitiethoadons() {
	return chitiethoadons;
}
public void setChitiethoadons(List<chitiethoadons> chitiethoadons) {
	this.chitiethoadons = chitiethoadons;
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
