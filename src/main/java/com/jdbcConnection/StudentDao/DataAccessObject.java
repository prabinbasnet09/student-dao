package com.jdbcConnection.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccessObject<S> {
	void connect() throws ClassNotFoundException, SQLException;
	void disconnect() throws SQLException;
	int save(S s) throws ClassNotFoundException, SQLException;
	S selectStudent(int id)throws ClassNotFoundException, SQLException;
	ArrayList<S> selectAllStudents()throws ClassNotFoundException, SQLException;
	int updateStudent(S s)throws ClassNotFoundException, SQLException;
	int deleteStudent(int id)throws ClassNotFoundException, SQLException;
}
