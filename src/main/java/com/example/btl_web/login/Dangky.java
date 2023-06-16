package com.example.btl_web.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.btl_web.connectDB.Connect;

import jakarta.servlet.http.HttpSession;


@Controller
public class Dangky {
	
	
	Connection conn = null;
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	
	
	@GetMapping("/user/{id_tk}")
	public String getPhone(Model model,@PathVariable String id_tk) {
		model.addAttribute("med",id_tk);
		String query = "select * from taikhoan where id_tk=?";
		
		Taikhoan tk = new Taikhoan();
		
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id_tk);
			rs = pstm.executeQuery();
			if(rs.next()) {
				tk.setId_tk(rs.getInt("id_tk"));
				tk.setName(rs.getString("name"));
				tk.setEmail(rs.getString("email"));
				tk.setPassword(rs.getString("password"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("tk", tk);
		
		return "dang-ky";
	}
	
	@PostMapping("/user/save/{id_tk}")
	public String addPhone(Taikhoan tk, @PathVariable String id_tk,HttpSession session) {
		String query = "insert into taikhoan values (0,?,?,?)";
		String queryKtra = "select * from taikhoan where name=?" ;
		try {
			conn = Connect.getConnection();
//			boolean check = true;
			Taikhoan tkCheck = null;
			
			pstm = conn.prepareStatement(queryKtra);
			pstm.setString(1, tk.getName());
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				tkCheck = new Taikhoan();
			}
			
			if(tkCheck == null) {
				//add phone
				pstm = conn.prepareStatement(query);
				
				pstm.setString(1, tk.getName());
				pstm.setString(2, tk.getEmail());
				pstm.setString(3,  tk.getPassword());
				
				pstm.execute();
				
				pstm.close();
				conn.close();
				session.setAttribute("user", tk.getEmail());
				return "redirect:/user";
			}
			else {
				return "error1";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return "error1";
		}
	}
}
