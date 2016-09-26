package com.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.entity.User;
import com.jdbc.util.DBUtil;

public class UserService {
	/* ÓÃ»§×¢²á */
	public boolean rigister(User user) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into user(username,password)values(?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("sqlÓï¾äÓÐ´í");
			e.printStackTrace();
		}

		return true;
	}

	/* ÓÃ»§µÇÂ¼ */
	public void login(User user) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		boolean flag = false;
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			flag = true;
		}
		if (flag)
			System.out.println("µÇÂ½³É¹¦");
		else {
			System.out.println("µÇÂ½Ê§°Ü");
		}
	}
}
