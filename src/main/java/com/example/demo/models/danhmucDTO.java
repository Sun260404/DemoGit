package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class danhmucDTO {
	@NotEmpty (message = "The 'tendanhmuc' is required")
	private String tendanhmuc;

	public String getTendanhmuc() {
		return tendanhmuc;
	}

	public void setTendanhmuc(String tendanhmuc) {
		this.tendanhmuc = tendanhmuc;
	}
	
}
