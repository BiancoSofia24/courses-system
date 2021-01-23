package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import app.controller.CourseController;
import app.controller.InscriptionsController;
import app.controller.StudentsController;
import app.controller.TeachersController;
import app.dao.AdminDB;
import app.utils.Util;

public class AppJDBC {

	public static void main(String[] args) {
		Util.showTitle("Escuelita");
		try {
			Connection con = AdminDB.getConnection();
			System.out.println("Conexión establecida...");
			Scanner scan = new Scanner(System.in);
			int option = showMenu(scan);
			while (option != 0) {
				switch (option) {
				case 1:
					students(scan, con);
					break;
				case 2:
					courses(scan, con);
					break;
				case 3:
					teachers(scan, con);
					break;
				case 4:
					inscriptions(scan, con);
					break;
				case 5:
					// fileLog(scan);
					break;
				}
				option = showMenu(scan);
			}
			con.close();
			System.out.println("Hasta Luego..!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void inscriptions(Scanner scan, Connection con) throws SQLException, IOException {
		int crudOption = InscriptionsController.showInscriptionsSubmenu(scan);
		InscriptionsController.inscriptionsOptions(crudOption, scan, con);
	}

	private static void teachers(Scanner scan, Connection con) throws SQLException {
		int crudOption = TeachersController.showTeachersSubmenu(scan);
		TeachersController.teachersOptions(crudOption, scan, con);
	}

	private static void students(Scanner scan, Connection con) throws SQLException, IOException {
		int crudOption = StudentsController.showStudentsSubmenu(scan);
		StudentsController.studentsOptions(crudOption, scan, con);
	}

	private static void courses(Scanner scan, Connection con) throws SQLException {
		int crudOption = CourseController.showCoursesSubmenu(scan);
		CourseController.coursesOptions(crudOption, scan, con);
	}

	private static int showMenu(Scanner scan) {
		Util.showTitle("Menú Principal");
		System.out.println("1 - Alumnos");
		System.out.println("2 - Cursos");
		System.out.println("3 - Profesores");
		System.out.println("4 - Inscripciones");
		System.out.println("5 - Registro de archivos (NO)");
		// method 1: create file with log (all files created) bufferedWritter.newLine()
		// for accumulative record
		// method 2: read the file as a backup
		System.out.println("0 - Salir");
		System.out.print("Opción -> ");
		return scan.nextInt();
	}

}
