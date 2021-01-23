package app.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import app.dao.AdminDB;
import app.dao.CoursesDAO;
import app.helper.CoursesHelper;
import app.model.Course;

public class CourseControllerTest {

	@Test
	public void shouldInsertNewCourse() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		int id = 15;
		String name = "OtroCurso";
		Course newCourse = new Course(id, name);
		List<Course> listBefore = CoursesDAO.findAll(con);
		CoursesHelper.insert(newCourse, con);
		List<Course> listAfter = CoursesDAO.findAll(con);
		assert (listBefore.size() < listAfter.size());
	}

}
