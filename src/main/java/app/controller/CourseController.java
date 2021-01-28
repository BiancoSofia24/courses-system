package app.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import app.dao.CoursesDAO;
import app.helper.CoursesHelper;
import app.model.Course;
import app.utils.Util;

public class CourseController {

	public static int showCoursesSubmenu(Scanner scan) {
		Util.showTitle("Menu Curso");
		System.out.println("1 - Nuevo Curso");
		System.out.println("2 - Ver Cursos");
		System.out.println("3 - Modificar Curso");
		System.out.println("4 - Eliminar Curso");
		System.out.println("5 - Buscar Curso por Nombre");
		System.out.println("0 - Ir Atras");
		System.out.print("Opcion -> ");
		return scan.nextInt();
	}

	public static void coursesOptions(int opt, Scanner scan, Connection con) throws SQLException {
		while (opt != 0) {
			switch (opt) {
			case 1:
				newCourse(scan, con);
				break;
			case 2:
				viewCourses(con);
				break;
			case 3:
				updateCourse(scan, con);
				break;
			case 4:
				deleteCourse(scan, con);
				break;
			case 5:
				findCourseByName(scan, con);
				break;
			}
			opt = showCoursesSubmenu(scan);
		}
	}

	public static void findCourseByName(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Buscar Curso por Nombre");
		String courseName = Util.requestNameInfo(scan, "nombre", "curso");
		List<Course> coursesListByName = CoursesDAO.findByName(courseName, con);
		CoursesHelper.showList(coursesListByName);
	}

	private static void deleteCourse(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Eliminar Curso");
		int idCourse = Util.requestId(scan, "curso");
		Course actualCourse = CoursesDAO.findById(idCourse, con);
		if (actualCourse == null) {
			Util.showError("Registro inexistente");
		} else {
			System.out.println(actualCourse);
			System.out.println();
			System.out.print("Seguro desea de eliminar este curso? y/n -> ");
			String opt = scan.next();
			if (opt.toUpperCase().equals("Y")) {
				CoursesHelper.delete(idCourse, con);
			} else if (opt.toUpperCase().equals("N")) {
				System.out.println("Registro no eliminado");
			}
		}
	}

	public static void updateCourse(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Modificar Curso");
		int idCourse = Util.requestId(scan, "curso");
		Course actualCourse = CoursesDAO.findById(idCourse, con);
		if (actualCourse == null) {
			Util.showError("Registro inexistente");
		} else {
			System.out.println(actualCourse);
			System.out.println();
			System.out.print("Desea editar este curso? y/n -> ");
			String opt = scan.next();
			if (opt.toUpperCase().equals("Y")) {
				System.out.println();
				String courseName = Util.requestNameInfo(scan, "nombre", "curso");
				Course course = new Course(idCourse, courseName);
				CoursesHelper.update(course, con);
			} else if (opt.toUpperCase().equals("N")) {
				System.out.println("Registro no editado");
			}
		}
	}

	public static void newCourse(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Nuevo Curso");
		String courseName = Util.requestNameInfo(scan, "nombre", "curso");
		Course course = new Course(courseName);
		CoursesHelper.insert(course, con);
	}

	public static void viewCourses(Connection con) throws SQLException {
		Util.showTitle("Lista de Cursos");
		List<Course> coursesList = CoursesDAO.findAll(con);
		CoursesHelper.showList(coursesList);
	}

}
