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
	public void shouldCUDCourse() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		String name = "test";
		Course course = new Course(name);
		int inserted = CoursesDAO.insert(course, con);
		assert (inserted == 1);

		List<Course> list = CoursesDAO.findByName(name, con);
		int id = list.get(0).getIdCourse();
		Course courseUpdated = new Course(id, "Updated");
		int updated = CoursesDAO.update(courseUpdated, con);
		assert (updated == 1);

		int deleted = CoursesDAO.delete(id, con);
		assert (deleted == 1);
	}

	@Test
	public void shouldInsertAndDeleteCourse() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		String name = "test";
		Course newCourse = new Course(name);
		CoursesHelper.insert(newCourse, con);
		List<Course> listBefore = CoursesDAO.findAll(con);
		List<Course> list = CoursesDAO.findByName(name, con);
		int id = list.get(0).getIdCourse();
		CoursesHelper.delete(id, con);
		List<Course> listAfter = CoursesDAO.findAll(con);
		assert (listBefore.size() > listAfter.size());
	}

}
