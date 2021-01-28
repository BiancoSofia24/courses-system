package app.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.dao.StudentsDAO;
import app.model.Student;
import app.utils.Util;

public class StudentsHelper {

	public static void showList(List<Student> list) {
		Util.showSubtitle("Id | Nombre   | Apellido    | Correo Electr�nico");
		for (Student item: list) {
			System.out.println(item.getIdStudent() + " | " + item.getsName() + " " + item.getsLastName() + " | "
					+ Util.valueForNullString(item.getsEmail()));
		}
	}

	public static void showListByLastName(List<Student> list) {
		Util.showSubtitle("Id | Apellido    | Nombre     | Correo Electr�nico");
		for (Student item: list) {
			System.out.println(item.getIdStudent() + " | " + item.getsLastName() + " | " + item.getsName() + " | "
					+ Util.valueForNullString(item.getsEmail()));
		}
	}

	public static void insert(Student student, Connection con) throws SQLException {
		int inserted = StudentsDAO.insert(student, con);
		if (inserted == 1) {
			System.out.println("Registro creado exitosamente");
		} else {
			Util.showError("Error de ingreso");
		}
	}

	public static void update(Student student, Connection con) throws SQLException {
		int updated = StudentsDAO.update(student, con);
		if (updated == 1) {
			System.out.println("Registro editado exitosamente");
		} else {
			Util.showError("Error en la edici�n de registro");
		}
	}

	public static void delete(int idStudent, Connection con) throws SQLException {
		int deleted = StudentsDAO.delete(idStudent, con);
		if (deleted == 1) {
			System.out.println("Registro eliminado");
		} else {
			Util.showError("Registro inexistente");
		}
	}

	public static void createFile(Student student, Connection con) throws SQLException, IOException {
		String directory = "src/inicio/db/inicio2/files/students/";
		String fileName = "Student#" + student.getIdStudent() + ".txt";
		String absolutePath = directory + fileName;
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath));
		String fileContent = fileContent(student, con);
		bufferedWriter.write(fileContent);
		bufferedWriter.close();
		System.out.println("Archivo creado exitosamente");
	}

	public static String fileContent(Student student, Connection con) throws SQLException {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = simpleDate.format(new Date());
		String fileContent = "Fecha de creaci�n: " + date + '\n' + '\n' + "Alumno #" + student.getIdStudent() + '\n'
				+ "-------------------" + '\n' + '\n' + "Nombre: " + student.getsName() + '\n' + "Apellido: "
				+ student.getsLastName() + '\n' + "Correo electr�nico: " + Util.valueForNullString(student.getsEmail());
		return fileContent;
	}

}
