package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Teacher;

public class TeachersDAO {

	public static int insert(Teacher teacher, Connection con) throws SQLException {
		String sql = "INSERT INTO teachers (tName, tLastName) VALUES (?, ?)";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, teacher.gettName());
		prepStmt.setString(2, teacher.gettLastName());
		return prepStmt.executeUpdate();
	}

	public static List<Teacher> findAll(Connection con) throws SQLException {
		List<Teacher> teachersList = new ArrayList<Teacher>();
		String sql = "SELECT * FROM teachers";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		ResultSet resultSet = prepStmt.executeQuery();
		Teacher teacher = null;
		while (resultSet.next()) {
			int idTeacher = resultSet.getInt(1);
			teacher = new Teacher(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5));
			teacher.setIdTeacher(idTeacher);
			teachersList.add(teacher);
		}
		return teachersList;
	}

	public static int update(Teacher teacher, Connection con) throws SQLException {
		String sql = "UPDATE teachers SET tName = ?, tLastName = ?, tEmail = ?, specialty = ? WHERE idTeacher = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, teacher.gettName());
		prepStmt.setString(2, teacher.gettLastName());
		prepStmt.setString(3, teacher.gettEmail());
		prepStmt.setString(4, teacher.getSpecialty());
		prepStmt.setInt(5, teacher.getIdTeacher());
		return prepStmt.executeUpdate();
	}

	public static int delete(int idTeacher, Connection con) throws SQLException {
		String sql = "DELETE FROM teachers WHERE idTeacher = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idTeacher);
		return prepStmt.executeUpdate();
	}

	public static Teacher findById(int idTeacher, Connection con) throws SQLException {
		String sql = "SELECT * FROM teachers WHERE idTeacher = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idTeacher);
		ResultSet resultSet = prepStmt.executeQuery();
		Teacher teacher = null;
		if (resultSet.next()) {
			teacher = new Teacher(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5));
		}
		return teacher;
	}

	public static List<Teacher> findByName(String teacherName, Connection con) throws SQLException {
		String sql = "SELECT * FROM teachers WHERE tName LIKE '%" + teacherName + "%' ORDER BY tName";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		List<Teacher> teachersList = new ArrayList<Teacher>();
		Teacher teacher = null;
		while (resultSet.next()) {
			teacher = new Teacher(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
			teachersList.add(teacher);
		}
		return teachersList;
	}

	public static List<Teacher> findByLastName(String teacherLName, Connection con) throws SQLException {
		String sql = "SELECT * FROM teachers WHERE tLastName LIKE '%" + teacherLName + "%' ORDER BY tLastName";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		List<Teacher> teachersList = new ArrayList<Teacher>();
		Teacher teacher = null;
		while (resultSet.next()) {
			teacher = new Teacher(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
			teachersList.add(teacher);
		}
		return teachersList;
	}
}
