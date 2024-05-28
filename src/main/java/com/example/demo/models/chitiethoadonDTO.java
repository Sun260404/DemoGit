package com.example.demo.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class chitiethoadonDTO {
	@NotNull (message = "The 'MaHoaDon_ID' is required")
	private int mahoadon_id;
	@NotNull (message = "The 'MaDoUong_ID' is required")
	private int madouong_id;
	
	@NotNull(message = "dongia must not be null")
    private Double dongia;
	
	@NotNull(message = "soluong must not be null")
	private double souong;

	public int getMahoadon_id() {
		return mahoadon_id;
	}

	public void setMahoadon_id(int mahoadon_id) {
		this.mahoadon_id = mahoadon_id;
	}

	public int getMadouong_id() {
		return madouong_id;
	}

	public void setMadouong_id(int madouong_id) {
		this.madouong_id = madouong_id;
	}

	public Double getDongia() {
		return dongia;
	}

	public void setDongia(Double dongia) {
		this.dongia = dongia;
	}

	public double getSouong() {
		return souong;
	}

	public void setSouong(double souong) {
		this.souong = souong;
	}
	
	


}
