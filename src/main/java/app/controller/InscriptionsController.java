package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import app.dao.CoursesDAO;
import app.dao.InscriptionsDAO;
import app.dao.StudentsDAO;
import app.helper.InscriptionsHelper;
import app.model.Course;
import app.model.Inscription;
import app.model.Student;
import app.utils.Util;

public class InscriptionsController {

	// Incomplete
	public static void inscriptionsOptions(int option, Scanner scan, Connection con) throws SQLException, IOException {
		while (option != 0) {
			switch (option) {
			case 1:
				newInscription(scan, con);
				break;
			case 2:
				viewInscriptions(con);
				break;
			case 3:
				updateInscription(scan, con);
				break;
			case 4:
				deleteInscription(scan, con);
				break;
			case 5:
				createFile(scan, con);
				break;
			case 6:
				searchCourseByStudent(scan, con);
				break;
			}
			option = showInscriptionsSubmenu(scan);
		}
	}

	private static void searchCourseByStudent(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Buscar Curso por Alumno");
		// give the option (for id, for name, for lastName)
		int idStudent = Util.requestId(scan, "alumno");
		List<Inscription> list = InscriptionsDAO.findCoursesByStudentId(idStudent, con);
		if (list.size() > 1) {
			InscriptionsHelper.showList(list, con);
		} else {
			Util.showError("No se encontraron registros");
		}
	}

	private static void createFile(Scanner scan, Connection con) throws SQLException, IOException {
		Util.showTitle("Crear archivo con registro");
		System.out.print("Ingrese id de la inscripci�n -> ");
		int idInsc = scan.nextInt();
		Inscription inscription = InscriptionsDAO.findById(idInsc, con);
		if (inscription == null) {
			Util.showError("Registro inexistente");
		} else {
			InscriptionsHelper.createFile(inscription, con);
		}
	}

	public static void deleteInscription(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Eliminar Inscripcion");
		int idInsc = Util.requestId(scan, "inscripcion");
		Inscription actualInscription = InscriptionsDAO.findById(idInsc, con);
		if (actualInscription == null) {
			System.err.println("Registro inexistente");
		} else {
			InscriptionsHelper.showInscription(actualInscription, con);
			System.out.println();
			System.out.print("Seguro desea de eliminar esta inscripcion? y/n -> ");
			String opt = scan.next();
			if (opt.toUpperCase().equals("Y")) {
				InscriptionsHelper.delete(idInsc, con);
			} else if (opt.toUpperCase().equals("N")) {
				System.out.println("Registro no eliminado");
			}
		}
	}

	// Incomplete
	public static void updateInscription(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Modificar Inscripcion");
		int idInsc = Util.requestId(scan, "inscripcion");
		Inscription actualInscription = InscriptionsDAO.findById(idInsc, con);
		if (actualInscription == null) {
			System.err.println("Registro inexistente");
		} else {
			InscriptionsHelper.showFullInscription(actualInscription, con);
		}
	}

	public static void viewInscriptions(Connection con) throws SQLException {
		Util.showTitle("Lista de Inscripciones");
		List<Inscription> inscriptionsList = InscriptionsDAO.findAll(con);
		InscriptionsHelper.showList(inscriptionsList, con);
	}

	public static void newInscription(Scanner scan, Connection con) throws SQLException {
		Util.showTitle("Nueva Inscripcion");
		int idStudent = Util.requestId(scan, "alumno registrado");
		Student student = StudentsDAO.findById(idStudent, con);
		if (student == null) {
			Util.showError("Registro inexistente");
		} else {
			System.out.println(student);
			int idCourse = Util.requestId(scan, "curso");
			Course course = CoursesDAO.findById(idCourse, con);
			if (course == null) {
				Util.showError("Registro inexistente");
			} else {
				System.out.println(course);
				System.out.println();
				System.out.print("Desea crear este registro? y/n -> ");
				String opt = scan.next();
				if (opt.toUpperCase().equals("Y")) {
					InscriptionsHelper.createValidInscription(student, course, con);
				} else if (opt.toUpperCase().equals("N")) {
					System.out.println("Registro no creado");
				}
			}
		}
	}

	// Incomplete
	public static int showInscriptionsSubmenu(Scanner scan) {
		Util.showTitle("Men� Inscripciones");
		System.out.println("1 - Nueva Inscripci�n");
		System.out.println("2 - Ver Inscripciones");
		System.out.println("3 - Modificar Inscripci�n");
		System.out.println("4 - Eliminar Inscripci�n");
		System.out.println("5 - Crear archivo con registro");
		System.out.println("6 - Buscar Cursos por Alumno");
		// Student per course. Search by teacher, status, commission, course
		System.out.println("Faltan opciones :)");
		System.out.println("0 - Ir Atr�s");
		System.out.print("Opci�n -> ");
		return scan.nextInt();
	}

}
