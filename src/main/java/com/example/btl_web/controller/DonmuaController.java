package com.example.btl_web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.btl_web.connectDB.Connect;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;





@Controller
public class DonmuaController {
		
	Connection conn = null;
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	@GetMapping("/dons")
	public String getDons(Model model,HttpSession session) throws IOException,ServletException{
		
//		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("user");
		
//		String query = "select book.title,book.author,book.category,book.rdate,book.page,donmua.soluong from donmua join book on donmua.id=book.id where donmua.email="+ userEmail;
		List<Donmua> dons = new ArrayList<>();
		String query = "select book.title,book.author,book.category,book.rdate,book.page,donmua.id_don,donmua.soluong from donmua join book on donmua.id = book.id where donmua.email = '" + userEmail + "'";
		try {
			conn = Connect.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			while(rs.next()) {
				Donmua don = new Donmua();
				
				don.setTitle(rs.getString("title"));
				don.setAuthor(rs.getString("author"));
				don.setCategory(rs.getString("category"));
				don.setRdate(rs.getDate("rdate"));
				don.setPage(rs.getInt("page"));
				don.setId_don(rs.getInt("id_don"));
				don.setSoluong(rs.getInt("soluong"));
				dons.add(don);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("dons", dons);
		return "donmua";
	}
	

	@GetMapping("/don/{id_don}")
	public String getDon(Model model,@PathVariable String id_don,HttpSession session) {
		model.addAttribute("med",id_don);
		String userEmail = (String) session.getAttribute("user");
		String query = "select book.title,book.author,book.category,book.rdate,book.page,donmua.soluong from donmua join book on donmua.id = book.id where donmua.email = '" + userEmail + "' and donmua.id_don=?";
		
		Donmua don = new Donmua();
		
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id_don);
			rs = pstm.executeQuery();
			if(rs.next()) {
//				don.setId_don(rs.getInt("id_don"));
				don.setTitle(rs.getString("title"));
				don.setAuthor(rs.getString("author"));
				don.setCategory(rs.getString("category"));
				don.setRdate(rs.getDate("rdate"));
				don.setPage(rs.getInt("page"));
				don.setSoluong(rs.getInt("soluong"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("don", don);
		return "don-detail";
	}
	
	
	
	@GetMapping("/delete1/{id_don}")
	public String deleteDon(@PathVariable String id_don,HttpSession session) {
		String query = "delete from donmua where id_don=?";
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id_don);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dons";
	}
	
	
	
	

	
}
