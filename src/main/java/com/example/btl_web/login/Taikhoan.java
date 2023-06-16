package com.example.btl_web.login;

public class Taikhoan {
	
	
	private int id_tk;
	private String name;
	private String email;
	private String password;
	
	
	public Taikhoan() {
		
	}
	
	public Taikhoan(int id_tk, String name, String email, String password) {
		this.id_tk = id_tk;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId_tk() {
		return id_tk;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setId_tk(int id_tk) {
		this.id_tk = id_tk;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
