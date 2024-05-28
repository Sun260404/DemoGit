package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
@Table(name = "menudouongs")
public class menudouongs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int madouong_id;

    private String tendouong;

    @ManyToOne
    @JoinColumn(name = "dongia_id", referencedColumnName = "dongia_id")
    private dongias dongia;

    @ManyToOne
    @JoinColumn(name = "madanhmuc_id", referencedColumnName = "madanhmuc_id")
    private danhmucs danhmuc;
    
    @OneToMany(mappedBy = "menudouong")
    @JsonBackReference
    private List<chitiethoadons> chitiethoadons = new ArrayList<>();

	public int getMadouong_id() {
		return madouong_id;
	}

	public void setMadouong_id(int madouong_id) {
		this.madouong_id = madouong_id;
	}

	public String getTendouong() {
		return tendouong;
	}

	public void setTendouong(String tendouong) {
		this.tendouong = tendouong;
	}

	public dongias getDongia() {
		return dongia;
	}

	public void setDongia(dongias dongia) {
		this.dongia = dongia;
	}

	public danhmucs getDanhmuc() {
		return danhmuc;
	}

	public void setDanhmuc(danhmucs danhmuc) {
		this.danhmuc = danhmuc;
	}

	public List<chitiethoadons> getChitiethoadons() {
		return chitiethoadons;
	}

	public void setChitiethoadons(List<chitiethoadons> chitiethoadons) {
		this.chitiethoadons = chitiethoadons;
	}

}
