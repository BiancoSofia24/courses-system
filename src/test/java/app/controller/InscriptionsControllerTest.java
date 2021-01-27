package app.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import app.dao.AdminDB;
import app.dao.CoursesDAO;
import app.dao.InscriptionsDAO;
import app.dao.StudentsDAO;
import app.helper.CoursesHelper;
import app.helper.InscriptionsHelper;
import app.helper.StudentsHelper;
import app.model.Course;
import app.model.Inscription;
import app.model.Student;

public class InscriptionsControllerTest {

	@Test
	public void createValidInscriptionTest() throws ClassNotFoundException, SQLException {
		Connection con = AdminDB.getConnection();
		Course firstCourse = createCourse("test");
		Student student = createStudent();
		int idStudent = student.getIdStudent();
		List<Inscription> listBeforeFirstTry = InscriptionsDAO.findCoursesByStudentId(idStudent, con);

		InscriptionsHelper.createValidInscription(student, firstCourse, con);
		List<Inscription> listAfterFirstTry = InscriptionsDAO.findCoursesByStudentId(idStudent, con);
		assert (listBeforeFirstTry.size() < listAfterFirstTry.size());

		Course secondCourse = createCourse("test2");
		InscriptionsHelper.createValidInscription(student, secondCourse, con);
		List<Inscription> listAfterSecondTry = InscriptionsDAO.findCoursesByStudentId(idStudent, con);
		assert (listAfterFirstTry.size() < listAfterSecondTry.size());

		Course thirdCourse = createCourse("test3");
		InscriptionsHelper.createValidInscription(student, thirdCourse, con);
		List<Inscription> listAfterThirdTry = InscriptionsDAO.findCoursesByStudentId(idStudent, con);
		assert (listAfterSecondTry.size() == listAfterThirdTry.size());

		InscriptionsDAO.delete(listAfterFirstTry.get(0).getIdInsc(), con);
		InscriptionsDAO.delete(listAfterSecondTry.get(0).getIdInsc(), con);
		CoursesDAO.delete(firstCourse.getIdCourse(), con);
		CoursesDAO.delete(secondCourse.getIdCourse(), con);
		CoursesDAO.delete(thirdCourse.getIdCourse(), con);
		StudentsDAO.delete(idStudent, con);
	}

	private Course createCourse(String name) throws SQLException, ClassNotFoundException {
		Connection con = AdminDB.getConnection();
		Course course = new Course(name);
		CoursesHelper.insert(course, con);
		return course;
	}

	private Student createStudent() throws SQLException, ClassNotFoundException {
		Connection con = AdminDB.getConnection();
		Student student = new Student("test", "asd", "asd@mail.com");
		StudentsHelper.insert(student, con);
		return student;
	}
}
