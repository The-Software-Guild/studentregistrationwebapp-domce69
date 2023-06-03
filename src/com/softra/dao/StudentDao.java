package com.softra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softra.model.Student;

public class StudentDao {
	@Autowired
	private JdbcTemplate template;
	
	public StudentDao() {
		System.out.println("Inside DAO Default Constructor.");
	}

	public Connection openConnection() { 
		//Type 4 Driver is registered with DriverManager
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL Driver registered with DriverManager");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student", "root", "root");
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Suitable Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return con;
	}
	
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create(Student student) {
		
		int id = student.getId();
		String name = student.getName();
		int age = student.getAge();
		int mobile = student.getMobile();
		String address = student.getAddress();
		
		Connection con = openConnection();
		
		try {
			//Statement stat = con.createStatement(); //using Statement
			//String sql = "INSERT INTO Student VALUES (" + id + ", '" + fname + "', '" + lname + "')";
			//int n = stat.executeUpdate(sql);
			
			//using PreparedStatement
			String sql = "INSERT INTO Student VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setInt(1, id);
			pstat.setString(2, name);
			pstat.setInt(3, age);
			pstat.setInt(4, mobile);
			pstat.setString(5, address);
			
			int n = pstat.executeUpdate();
			
			System.out.println("Number of rows inserted: " + n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(con);
	}
	
	
}
