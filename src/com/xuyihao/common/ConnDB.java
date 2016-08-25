package com.xuyihao.common;

import java.sql.*;
import java.util.Properties;

public class ConnDB {
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	private static Properties propForManager = new Properties();
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/EBTest";

	static {
		Properties prop = AppPropertiesLoader.getAppProperties();
		dbClassName = prop.getProperty("DB_CLASS_NAME");
		dbUrl = prop.getProperty("DB_URL");
		propForManager.put("user", prop.getProperty("DB_USER"));
		propForManager.put("password", prop.getProperty("DB_PASSWORD"));
		propForManager.put("characterEncoding", prop.getProperty("DB_ENCODIND"));
		propForManager.put("useSSL", prop.getProperty("useSSL"));
		propForManager.put("useUnicode", prop.getProperty("useUnicode"));
	}

	/**
	 * constructor
	 */
	public ConnDB() {
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, propForManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			System.err.println("连接数据库发生错误!");
		}
		return conn;
	}

	/**
	 * 进行查询
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * 进行更新
	 * 
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/**
	 * 关闭数据库连接对象
	 */
	public void close() {
		try {
			if ((rs != null) && (!rs.isClosed())) {
				rs.close();
			}
			if ((stmt != null) && (!stmt.isClosed())) {
				stmt.close();
			}
			if ((conn != null) && (!conn.isClosed())) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
