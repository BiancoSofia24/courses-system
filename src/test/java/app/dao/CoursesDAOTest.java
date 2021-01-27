package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import app.model.Course;

public class CoursesDAOTest {

	@Test
	public void shouldFindAllCoursesTest() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		List<Course> list = CoursesDAO.findAll(con);
		assert (list.size() > 0);
	}

	@Test
	public void shouldfindCourseByIdTest() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		int id = 1;
		String sql = "SELECT * FROM courses WHERE idCourse = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, id);
		ResultSet resultSet = prepStmt.executeQuery();
		assert (resultSet.next());
	}

	@Test
	public void shouldfindCourseByNameTest() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		String name = "Java";
		String sql = "SELECT * FROM courses WHERE cName LIKE '%" + name + "%' ORDER BY cName";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		assert (resultSet.next());
	}

	@Test
	public void shouldCUDCourseTest() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		String name = "test";
		String sql = "INSERT INTO courses (cName) VALUES (?)";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, name);
		int inserted = prepStmt.executeUpdate();
		assert (inserted == 1);

		List<Course> list = CoursesDAO.findByName(name, con);
		int id = list.get(0).getIdCourse();
		name = list.get(0).getcName();
		sql = "UPDATE courses SET cName = ? WHERE idCourse = ?";
		prepStmt = con.prepareStatement(sql);
		prepStmt.setString(1, name);
		prepStmt.setInt(2, id);
		int updated = prepStmt.executeUpdate();
		assert (updated == 1);

		sql = "DELETE FROM courses WHERE idCourse = ?";
		prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, id);
		prepStmt.executeUpdate();
		Course course = CoursesDAO.findById(id, con);
		assert (course == null);
	}
}
