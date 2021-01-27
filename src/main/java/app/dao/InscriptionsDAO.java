package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Inscription;

public class InscriptionsDAO {

	public static int insert(Inscription inscription, Connection con) throws SQLException {
		String sql = "INSERT INTO inscriptions (id_student, id_course, status) VALUES (?, ?, ?)";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, inscription.getStudent().getIdStudent());
		prepStmt.setInt(2, inscription.getCourse().getIdCourse());
		prepStmt.setString(3, inscription.getStatus());
		return prepStmt.executeUpdate();
	}

	public static List<Inscription> findAll(Connection con) throws SQLException {
		List<Inscription> inscriptionsList = new ArrayList<Inscription>();
		String sql = "SELECT * FROM inscriptions ORDER BY idInsc";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		ResultSet resultSet = prepStmt.executeQuery();
		while (resultSet.next()) {
			Inscription inscription = new Inscription(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
					resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7),
					resultSet.getString(8));
			inscriptionsList.add(inscription);
		}
		return inscriptionsList;
	}

	public static int update(Inscription inscription, Connection con) {
		// Update inscriptions.
		// Fields student, course, teacher, commission, partialNote, finalNota, status
		return 0;
	}

	public static int delete(int idInsc, Connection con) throws SQLException {
		String sql = "DELETE FROM inscriptions WHERE idInsc = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idInsc);
		return prepStmt.executeUpdate();
	}

	public static Inscription findById(int idInsc, Connection con) throws SQLException {
		String sql = "SELECT * FROM inscriptions WHERE idInsc = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idInsc);
		ResultSet resultSet = prepStmt.executeQuery();
		Inscription inscription = null;
		if (resultSet.next()) {
			inscription = new Inscription(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
					resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7),
					resultSet.getString(8));
		}
		return inscription;
	}

	public static List<Inscription> findCoursesByStudentId(int idStudent, Connection con) throws SQLException {
		List<Inscription> list = new ArrayList<Inscription>();
		String sql = "SELECT i.idInsc, i.id_student, i.id_course FROM inscriptions i, students s WHERE i.id_student = ? AND s.idStud = ?";
		PreparedStatement prepStmt = con.prepareStatement(sql);
		prepStmt.setInt(1, idStudent);
		prepStmt.setInt(2, idStudent);
		ResultSet resultSet = prepStmt.executeQuery();
		Inscription inscription = null;
		while (resultSet.next()) {
			inscription = new Inscription(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
			list.add(inscription);
		}
		return list;
	}
}
