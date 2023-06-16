package com.example.btl_web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.btl_web.connectDB.Connect;
import com.example.btl_web.login.Taikhoan;

import org.apache.commons.lang3.StringUtils;







@Controller
public class MainController {
	
	
	Connection conn = null;
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	@GetMapping("/main")
	public String get(Model model) throws IOException  {
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
		return "main";
	}
	
	
	@GetMapping("/admin")
	public String getBooks(Model model) throws IOException  {
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
				book.setCategory(rs.getString("category"));
				book.setRdate(rs.getDate("rdate"));
				book.setPage(rs.getInt("page"));
				book.setSold(rs.getInt("sold"));
				book.setEnt(rs.getString("ent"));
				books.add(book);
				
			}
		}
		// End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("books", books);
		return "admin";
	}
	
	
	@GetMapping("/admin/{id}")
	public String getBook(Model model,@PathVariable String id) {
		model.addAttribute("med",id);
		String query = "select * from book where id=?";
		
		Book book = new Book();
		
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setEnt(rs.getString("ent"));
				book.setRdate(rs.getDate("rdate"));
				book.setPage(rs.getInt("page"));
				book.setCategory(rs.getString("category"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("book", book);
		return "admin-detail";
	}
	
	
	
	@GetMapping("/admin/edit/{id}")
	public String getBookEdit(Model model,@PathVariable String id) {
		model.addAttribute("med",id);
		String query = "select * from book where id=?";
		
		Book book = new Book();
		
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setEnt(rs.getString("ent"));
				book.setRdate(rs.getDate("rdate"));
				book.setPage(rs.getInt("page"));
				book.setCategory(rs.getString("category"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("book", book);
		return "admin-change";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable String id) {
		String query = "delete from book where id=?";
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	
	}
	
	
	@PutMapping("/admin/edit/save/{id}")
	public String updateBook(Book book, @PathVariable String id) {
		String query = "update book set title=?, author=?, category=?,rdate=?,page=?,sold=?,ent=? where id=?";
		try {
			conn = Connect.getConnection();
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setString(3,  book.getCategory());
			pstm.setDate(4, book.getRdate());
			pstm.setInt(5, book.getPage());
			pstm.setInt(6, book.getSold());
			pstm.setString(7,  book.getEnt());
			pstm.setInt(8, book.getId());
			pstm.execute();
			
			pstm.close();
			conn.close();
			
			return "redirect:/admin";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "redirect:/admin";
		}	
	}
		
//	@PostMapping("/admin/edit/save/{id}")
//	public String addBook(Book book, @PathVariable String id) {
//			String query = "insert into book(id,title,author,category,rdate,page,ent) values (0,?,?,?,?,?,?)";
//			String queryKtra = "select * from book where title=? and author=?" ;
//			
//			try {
//				conn = Connect.getConnection();
//				Book bookCheck = null;
//				pstm = conn.prepareStatement(queryKtra);
//				pstm.setString(1, book.getTitle());
//				pstm.setString(2, book.getAuthor());
//				rs = pstm.executeQuery();
//				
//				if(rs.next()) {
//					bookCheck = new Book();
//				}
//				
//				if(bookCheck == null) {
//					pstm = conn.prepareStatement(query);
//					
////					pstm.setInt(1, book.getId());
//					pstm.setString(1, book.getTitle());
//					pstm.setString(2, book.getAuthor());
//					pstm.setString(3,  book.getCategory());
//					pstm.setDate(4, book.getRdate());
//					pstm.setInt(5, book.getPage());
//
//					pstm.setString(6,  book.getEnt());
//					pstm.execute();
//					
//					pstm.close();
//					conn.close();
//					return "redirect:/admin";
//				}
//				else {
//					return "error";
//				}
//					
//					
//				
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//				return "error";
//			}
//			
//		}
	
	@PostMapping("/admin/edit/save/{id}")
	public String addBook(@ModelAttribute("book") Book book, @PathVariable String id, @RequestParam("imageFile") MultipartFile imageFile) {
		String query = "insert into book(id,title,author,category,rdate,page,ent,link) values (0,?,?,?,?,?,?,?)";
		String queryKtra = "select * from book where title=? and author=?";
	
		try {
		    Connection conn = Connect.getConnection();
		    PreparedStatement pstmCheck = conn.prepareStatement(queryKtra);
		    pstmCheck.setString(1, book.getTitle());
		    pstmCheck.setString(2, book.getAuthor());
		    ResultSet rs = pstmCheck.executeQuery();
		    
		    if(rs.next()) {
		        return "error";
		    }
		    
		    PreparedStatement pstm = conn.prepareStatement(query);
		    pstm.setInt(1, book.getId());
		    pstm.setString(2, book.getTitle());
		    pstm.setString(3, book.getAuthor());
		    pstm.setString(4, book.getCategory());
		    pstm.setDate(5, book.getRdate());
		    pstm.setInt(6, book.getPage());
		    pstm.setString(7, book.getEnt());
	
		    // Chuyển đổi file ảnh thành byte array
		    byte[] imageData = imageFile.getBytes();
		    pstm.setBytes(8, imageData);
		    
		    pstm.executeUpdate();
		    pstm.close();
		    conn.close();
		    return "redirect:/admin";
		        
		} catch (Exception e) {
		    e.printStackTrace();
		    return "error";
		}
	}
}
