package com.mysql.test.mysqlconnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

public class JDBCTest01 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			com.mysql.test.mysqlconnect.App.main(args);
			// 注册 JDBC 驱动
			Class.forName("com.mysql.cj.jdbc.Driver");

			List<String> list = new ArrayList<String>();
			
			list.add("yxy");

			// 1. 注册驱动
			// 1.1 获取驱动对象
			Driver driver = new com.mysql.jdbc.Driver();
			// 1.2 注册驱动
			DriverManager.registerDriver(driver);
			// 2. 获取数据库连接
			String url = "jdbc:mysql://localhost:3306/northdev?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
			String user = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);

			System.out.println(conn);// 打印对象，打印出来即连接成功
			// 3. 获取数据库操作对象
			stmt = conn.createStatement();

			System.out.println(stmt);// 打印对象
			// 4. 执行DQL语句
			String sql = "select * from sys_user";

			rs = stmt.executeQuery(sql);

			System.out.println(rs);// 打印对象
			// 5. 处理查询结果集
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(14);

				System.out.println(id + " " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 关闭资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}