package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Course;

public class CoursesDAO {

	public static int insert(Course course, Connection con) throws SQLException {
		String sql = "INSERT INTO courses (cName) VALUES (?)";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, course.getcName());
		return prepStmt.executeUpdate();
	}

	public static List<Course> findAll(Connection con) throws SQLException {
		List<Course> courseList = new ArrayList<Course>();
		String sql = "SELECT * FROM courses";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		ResultSet resultSet = prepStmt.executeQuery();
		Course course = null;
		while (resultSet.next()) {
			int idCourse = resultSet.getInt(1);
			course = new Course(resultSet.getString(2));
			course.setIdCourse(idCourse);
			courseList.add(course);
		}
		return courseList;
	}

	public static int update(Course course, Connection con) throws SQLException {
		String sql = "UPDATE courses SET cName = ? WHERE idCourse = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, course.getcName());
		prepStmt.setInt(2, course.getIdCourse());
		return prepStmt.executeUpdate();
	}

	public static int delete(int idCourse, Connection con) throws SQLException {
		String sql = "DELETE FROM courses WHERE idCourse = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idCourse);
		return prepStmt.executeUpdate();
	}

	public static Course findById(int idCourse, Connection con) throws SQLException {
		String sql = "SELECT * FROM courses WHERE idCourse = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idCourse);
		ResultSet resultSet = prepStmt.executeQuery();
		Course course = null;
		if (resultSet.next()) {
			course = new Course(resultSet.getInt(1), resultSet.getString(2));
		}
		return course;
	}

	public static List<Course> findByName(String courseName, Connection con) throws SQLException {
		String sql = "SELECT * FROM courses WHERE cName LIKE '%" + courseName + "%' ORDER BY cName";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		List<Course> courseList = new ArrayList<Course>();
		Course course = null;
		while (resultSet.next()) {
			course = new Course(resultSet.getInt(1), resultSet.getString(2));
			courseList.add(course);
		}
		return courseList;
	}
}
