package com.jdbcConnection.StudentDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDataAccessObject<S> implements DataAccessObject<S>{
	String driver = "com.mysql.cj.jdbc.Driver";
	String dbUrl = "jdbc:mysql://localhost:3306/Student";
	String username = "root";
	String password = "admindatabase";
	Connection con;
	
	@Override
	public void connect() throws ClassNotFoundException, SQLException {
		//loads the JDBC driver
		Class.forName(driver);
		con = DriverManager.getConnection(dbUrl, username, password);
	}
	
	@Override
	public void disconnect() throws SQLException {
		con.close();
	}
}
