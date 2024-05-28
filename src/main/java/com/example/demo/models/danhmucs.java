package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "danhmucs")
public class danhmucs  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int madanhmuc_id;
	private String tendanhmuc;
	@OneToMany(mappedBy = "danhmuc")
	@JsonBackReference
	private List<menudouongs> menudouongs = new ArrayList<>();
	
	
	
	
	
	public int getMadanhmuc_id() {
		return madanhmuc_id;
	}
	public void setMadanhmuc_id(int madanhmuc_id) {
		this.madanhmuc_id = madanhmuc_id;
	}
	public String getTendanhmuc() {
		return tendanhmuc;
	}
	public void setTendanhmuc(String tendanhmuc) {
		this.tendanhmuc = tendanhmuc;
	}
	public List<menudouongs> getMenudouongs() {
		return menudouongs;
	}
	public void setMenudouongs(List<menudouongs> menudouongs) {
		this.menudouongs = menudouongs;
	}
	

}
