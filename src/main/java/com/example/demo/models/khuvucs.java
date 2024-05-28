package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "khuvucs")
public class khuvucs {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int makhuvuc_id;
	private String tenkhuvuc;
	@Column(columnDefinition = "TEXT")
	private String mota;
	@OneToMany(mappedBy = "khuvuc")
	@JsonBackReference
	private List<bans> bans = new ArrayList<>();
	
	
	
	public int getMakhuvuc_id() {
		return makhuvuc_id;
	}

	public void setMakhuvuc_id(int makhuvuc_id) {
		this.makhuvuc_id = makhuvuc_id;
	}

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
