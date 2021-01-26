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
	public void shouldUpdateCourse() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		int id = 18;
		Course courseBefore = CoursesDAO.findById(id, con);
		String nameBefore = courseBefore.getcName();
		Course courseUpdated = new Course(id, "Updated");
		CoursesHelper.update(courseUpdated, con);
		Course courseAfter = CoursesDAO.findById(id, con);
		String nameAfter = courseAfter.getcName();
		courseUpdated = new Course(id, nameBefore);
		CoursesHelper.update(courseUpdated, con);
		int compare = nameBefore.compareTo(nameAfter);
		assert (compare != 0);
	}

	@Test
	public void shouldInsertAndDeleteCourse() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		String name = "test000";
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
