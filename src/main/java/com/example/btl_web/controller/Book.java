package com.example.btl_web.controller;

import java.sql.Date;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String category;
	private Date rdate;
	private int page;
	private int sold;
	private String ent;
	private String link;
	private int price;
	private int soluong;
	private int danhgia;
	private String nhanxet;
	
	
	public Book() {
		
	}
	
	public Book( int id,String title, String author,String category,Date rdate,int page,int sold,String ent,String link,int price,int soluong,int danhgia,String nhanxet) {
		
		this.id = id;
		this.title = title;
		this.author =author;
		this.category = category;
		this.rdate = rdate;
		this.page = page;
		this.sold = sold;
		this.ent =ent;
		this.link = link;
		this.price = price;
		this.soluong =soluong;
		this.danhgia =danhgia;
		this.nhanxet = nhanxet;
	}

	
	
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
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

	public int getSold() {
		return sold;
	}

	public String getEnt() {
		return ent;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setSold(int sold) {
		this.sold = sold;
	}

	public void setEnt(String ent) {
		this.ent = ent;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
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
	
	
	
}
