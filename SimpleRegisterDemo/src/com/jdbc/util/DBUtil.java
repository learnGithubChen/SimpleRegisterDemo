package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbcdemo?serverTimezone=UTC&characterEncoding=utf8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mysql123";
	private static Connection conn = null;
	static {
		try {
			// 加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 获得连接
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return conn;
	}
}
