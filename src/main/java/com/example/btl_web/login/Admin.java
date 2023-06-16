package com.example.btl_web.login;

public class Admin {
	
	private int id_id;
	private String name;
	private String email;
	private String password;
	
	public Admin() {
		
	}
	
	public Admin(int id_id, String name, String email, String password) {
		this.id_id = id_id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId_id() {
		return id_id;
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

	public void setId_id(int id_id) {
		this.id_id = id_id;
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
