package com.example.btl_web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.btl_web.connectDB.Connect;


import jakarta.servlet.http.HttpSession;


@Controller
public class User {
	
	
	Connection conn = null;
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	
	@GetMapping("/user")

	public String getUser(Model model) throws IOException  {
		List<Book> books = new ArrayList<>();
		String query = "select * from book";
		try {			
			conn = Connect.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setLink(rs.getString("link"));
				book.setPrice(rs.getInt("price"));
				books.add(book);
				
			}
		}
		// End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("books", books);
		return "user";
	}
	
	
	@GetMapping("/userbook/{id}")
	public String getBook(Model model,@PathVariable String id,HttpSession session) {
		model.addAttribute("med",id);
		String userEmail = (String) session.getAttribute("user");
		String query = "select book.id,book.link,book.title,book.author,book.price,book.sold, COALESCE(donmua.soluong,0) as soluong from book left join donmua on donmua.id = book.id and donmua.email='" + userEmail + "' where book.id=?" ;
		
//		String querydon = "select donmua.soluong from donmua join book where donmua.id=book.id";
		
		Book book = new Book();
	
		
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				book.setId(rs.getInt("id"));
				book.setLink(rs.getString("link"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setSold(rs.getInt("sold"));
//				book.setCategory(rs.getString("category"));
//				book.setEnt(rs.getString("ent"));
//				book.setRdate(rs.getDate("rdate"));
//				book.setPage(rs.getInt("page"));
				book.setSoluong(rs.getInt("soluong"));
//				book.setDanhgia(rs.getInt("danhgia"));
//				book.setNhanxet(rs.getString("nhanxet"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("book", book);
	
		return "user-detail";
	}
	
	

	
	@PostMapping("/userbook/save/{id}")
	public String addBook(Donmua dm, @PathVariable String id, HttpSession session) {
	    String userEmail = (String) session.getAttribute("user");
	    String query = "insert into donmua(id_don,soluong,id,email,danhgia,nhanxet) values(0,?,?,?,?,?)";

	    try {
	        conn = Connect.getConnection();
	        pstm = conn.prepareStatement(query);
	        pstm.setInt(1, dm.getSoluong());
	        pstm.setInt(2, dm.getId());
	        pstm.setString(3, userEmail);
	        pstm.setInt(4, dm.getDanhgia());
	        pstm.setString(5, dm.getNhanxet());

	        
	        pstm.execute();
	        
	        pstm.close();
	        conn.close();
	        return "redirect:/user";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/user";
	    }
	}
	
//	@PutMapping("/userbook/save/{id}")
//	public String updateDon(Donmua don) {
//		
//		String query = "update donmua set soluong=?,danhgia=?,nhanxet=? where id_don=? and id=?";
//		try {
//			conn = Connect.getConnection();
//			pstm = conn.prepareStatement(query);
//			
//			pstm.setInt(1, don.getSoluong());
//			
//
//			pstm.setInt(2, don.getDanhgia());
//			pstm.setString(3, don.getNhanxet());
//			pstm.setInt(4, don.getId_don());
//			pstm.setInt(5, don.getId());
//	
//			
//			pstm.execute();
//			
//			pstm.close();
//			conn.close();
//			
//			return "redirect:/user";
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return "error1";
//		}		
//	}
	
	
	
	
	
}
