package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "bans")
public class bans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maban_id;
	private String tenban;
	private String trangthai;
	private String mota;
	@ManyToOne
	@JoinColumn(name = "makhuvuc_id", referencedColumnName = "makhuvuc_id")
	private khuvucs khuvuc;
	
	@OneToMany(mappedBy="ban")
	@JsonBackReference
	private List<hoadons> hoadons = new ArrayList<>();
	
	
	
	public int getMaban_id() {
		return maban_id;
	}
	public void setMaban_id(int maban_id) {
		this.maban_id = maban_id;
	}
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
	public khuvucs getKhuvuc() {
		return khuvuc;
	}
	public void setKhuvuc(khuvucs khuvuc) {
		this.khuvuc = khuvuc;
	}
}