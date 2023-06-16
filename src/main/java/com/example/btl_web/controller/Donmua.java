package com.example.btl_web.controller;

import java.sql.Date;

public class Donmua {
	
	private String title;
	private String author;
	private String category;
	private Date rdate;
	private int page;
	private int id_don;
	private int soluong;
	private int id;
	private String email;
	private int danhgia;
	private String nhanxet;
	
	
	
	public Donmua() {
		
	}
	
	public Donmua(String title,String author,String category,Date rdate,Integer page,int id_don,int soluong,int id,String email,int danhgia,String nhanxet) {
		this.title = title;
		this.author =author;
		this.category = category;
		this.rdate = rdate;
		this.page = page;

//		this.ent =ent;
		this.id_don = id_don;
		this.soluong = soluong;

		this.id = id;
		this.email =email;
		this.danhgia = danhgia;
		this.nhanxet = nhanxet;
	}

	
	
	
	
	public int getDanhgia() {
		return danhgia;
	}

	public String getNhanxet() {
		return nhanxet;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}

	public void setNhanxet(String nhanxet) {
		this.nhanxet = nhanxet;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public Date getRdate() {
		return rdate;
	}

	public int getPage() {
		return page;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId_don() {
		return id_don;
	}

	public int getSoluong() {
		return soluong;
	}

//	public String getDanhgia() {
//		return danhgia;
//	}

//	public String getNhanxet() {
//		return nhanxet;
//	}

	

	public int getId() {
		return id;
	}

	public void setId_don(int id_don) {
		this.id_don = id_don;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

//	public void setDanhgia(String danhgia) {
//		this.danhgia = danhgia;
//	}
//
//	public void setNhanxet(String nhanxet) {
//		this.nhanxet = nhanxet;
//	}

	

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
