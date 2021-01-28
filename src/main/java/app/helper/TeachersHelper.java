package app.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import app.dao.TeachersDAO;
import app.model.Teacher;
import app.utils.Util;

public class TeachersHelper {

	public static void showList(List<Teacher> list) {
		Util.showSubtitle("Id | Nombre | Apellido  | Correo Electr�nico   | Especialidad");
		for (Teacher item: list) {
			System.out.println(item.getIdTeacher() + " | " + item.gettName() + " | " + item.gettLastName() + " | "
					+ Util.valueForNullString(item.gettEmail()) + " | " + Util.valueForNullString(item.getSpecialty()));
		}
	}

	public static void showListByLastName(List<Teacher> list) {
		Util.showSubtitle("Id | Apellido | Nombre  | Correo Electr�nico   | Especialidad");
		for (Teacher item: list) {
			System.out.println(item.getIdTeacher() + " | " + item.gettLastName() + " " + item.gettName() + " | "
					+ Util.valueForNullString(item.gettEmail()) + " | " + Util.valueForNullString(item.getSpecialty()));
		}
	}

	public static void insert(Teacher teacher, Connection con) throws SQLException {
		int inserted = TeachersDAO.insert(teacher, con);
		if (inserted == 1) {
			System.out.println("Registro creado exitosamente");
		} else {
			Util.showError("Error de ingreso");
		}
	}

	public static void update(Teacher teacher, Connection con) throws SQLException {
		int updated = TeachersDAO.update(teacher, con);
		if (updated == 1) {
			System.out.println("Profesor editado correctamente");
		} else {
			Util.showError("Error en la edici�n de registro");
		}
	}

	public static void delete(int idTeacher, Connection con) throws SQLException {
		int deleted = TeachersDAO.delete(idTeacher, con);
		if (deleted == 1) {
			System.out.println("Registro eliminado");
		} else {
			Util.showError("Registro inexistente");
		}
	}

}
