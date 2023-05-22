package com.jdbcConnection.StudentDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao extends AbstractDataAccessObject<Student>{
	String query;
	ResultSet rs;
	PreparedStatement pstm;
	int i;
	
	@Override
	public int save(Student s) throws ClassNotFoundException, SQLException {
		query = """
					insert into student (name, email, active)
					values(?, ?, ?)
				""";
		connect();
		pstm=con.prepareStatement(query);
		pstm.setString(1, s.getName());
		pstm.setString(2, s.getEmail());
		pstm.setBoolean(3, s.isActive());
		i = pstm.executeUpdate();
		disconnect();
		return i;
	}

	@Override
	public Student selectStudent(int id) throws ClassNotFoundException, SQLException {
		query = """
					select * from student
					where id=?
				""";
		connect();
		pstm = con.prepareStatement(query);
		pstm.setInt(1, id);
		rs = pstm.executeQuery();
		Student student = new Student();
		if(rs.next()) {
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setEmail(rs.getString("email"));
			student.setActive(rs.getBoolean("active"));
			disconnect();
			return student;
		}
		return null;
	}

	@Override
	public ArrayList<Student> selectAllStudents() throws ClassNotFoundException, SQLException {
		query = """
				select * from student
				""";
		connect();
		pstm = con.prepareStatement(query);
		rs = pstm.executeQuery();
		ArrayList<Student> studentList = new ArrayList<>();
		if(rs.next()) {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setEmail(rs.getString("email"));
			student.setActive(rs.getBoolean("active"));
			studentList.add(student);
		}
		disconnect();
		return studentList;
	}

	@Override
	public int updateStudent(Student s) throws ClassNotFoundException, SQLException {
		query = """
				update student set name=?, email=?, active=?
				where id=?
			""";
		connect();
		pstm=con.prepareStatement(query);
		pstm.setString(1, s.getName());
		pstm.setString(2, s.getEmail());
		pstm.setBoolean(3, s.isActive());
		pstm.setInt(4, s.getId());
		i = pstm.executeUpdate();
		disconnect();
		return i;
	}

	@Override
	public int deleteStudent(int id) throws ClassNotFoundException, SQLException {
		query = """
					delete from student
					where id=?
				""";
		connect();
		pstm = con.prepareStatement(query);
		pstm.setInt(1, id);
		int i = pstm.executeUpdate();
		return i;
	}
}
