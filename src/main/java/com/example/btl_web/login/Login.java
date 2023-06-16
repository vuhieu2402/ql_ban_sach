package com.example.btl_web.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.standard.expression.AndExpression;

import com.example.btl_web.connectDB.Connect;

import jakarta.servlet.http.HttpSession;




@Controller
public class Login {
	
	
	Connection conn = null;
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	

	
	public boolean isUserLoggedIn(Taikhoan tk,Model model) {
		String queryKtra = "select * from taikhoan where email=? and password=?";
		
		try {
			conn = Connect.getConnection();

			Taikhoan adCheck = null;
				
						
			pstm = conn.prepareStatement(queryKtra);
			
			pstm.setString(1, tk.getEmail());
			pstm.setString(2, tk.getPassword());
						
			rs = pstm.executeQuery();
			model.addAttribute("tk", tk);
						
						
						
			if(rs.next()) {
				adCheck = new Taikhoan();
						
			}
						
			if(adCheck != null ) {
							
				return true;
							
			}
			else {
				return false;
			}
		}
			
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isAdminLoggedIn(Admin ad,Model model) {
		String queryKtra = "select * from admin where email=? and password=?";
		
		try {
			conn = Connect.getConnection();

			Admin adCheck = null;
				
						
			pstm = conn.prepareStatement(queryKtra);
			
			pstm.setString(1, ad.getEmail());
			pstm.setString(2, ad.getPassword());
						
			rs = pstm.executeQuery();
			model.addAttribute("ad", ad);
						
						
						
			if(rs.next()) {
				adCheck = new Admin();
						
			}
						
			if(adCheck != null ) {
							
				return true;
							
			}
			else {
				return false;
			}
		}
			
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping("/login")
	public String checkLog(Taikhoan tk,Admin ad, Model model, HttpSession session) {

	    // Kiểm tra đăng nhập của người dùng
	    
	    if (isUserLoggedIn(tk, model)) { // Nếu đăng nhập thành công với vai trò user
	        // Lưu thông tin tài khoản vào Session
	        session.setAttribute("user", tk.getEmail());
	        // Chuyển hướng sang trang user
	        return "redirect:/user";
	    } else if (isAdminLoggedIn(ad, model)) { // Nếu đăng nhập thành công với vai trò admin
	        // Lưu thông tin tài khoản vào Session
	        session.setAttribute("admin", ad);
	        // Chuyển hướng sang trang admin
	        return "redirect:/admin";
	    } else { // Nếu đăng nhập thất bại
	        // Hiển thị thông báo lỗi đăng nhập trên trang login
	        model.addAttribute("error", "Invalid username or password");
	        return "login";
	    }
	}
}
