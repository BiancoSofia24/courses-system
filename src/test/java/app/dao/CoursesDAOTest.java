package app.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import app.model.Course;

public class CoursesDAOTest {

	@Test
	public void shouldFindAllCourses() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		List<Course> list = CoursesDAO.findAll(con);
		assert (list.size() > 0);
	}

	@Test
	public void shouldInsertCourseDAO() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		int id = 14;
		String name = "NuevoCurso";
		Course course = new Course(id, name);
		int inserted = CoursesDAO.insert(course, con);
		assert (inserted == 1);
	}
}
