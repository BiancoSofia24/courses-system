package app.model;

public class Course {

	private int idCourse;
	private String cName;

	public Course(String courseName) {
		this.cName = courseName;
	}

	public Course(int idCourse, String courseName) {
		this.idCourse = idCourse;
		this.cName = courseName;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String toString() {
		return "Curso: " + this.cName;
	}
}
