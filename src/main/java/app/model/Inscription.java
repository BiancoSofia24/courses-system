package app.model;

public class Inscription {

	private int idInsc;
	private int idStudent;
	private int idTeacher;
	private int idCourse;
	private String commission; // For update type -> String to Int
	private int partialNote;
	private int finalNote;
	private String status; // 1 = activo / 0 = cancelado

	private Course course;
	private Student student;
	private Teacher teacher;

	public Inscription(int idStudent, int idCourse, int idTeacher, String commission, int partialNote, int finalNote,
			String status) {
		this.idStudent = idStudent;
		this.idCourse = idCourse;
		this.idTeacher = idTeacher;
		this.commission = commission;
		this.partialNote = partialNote;
		this.finalNote = finalNote;
		this.status = status;
	}

	public Inscription(Student student, Course course, String status) {
		this.student = student;
		this.course = course;
		this.status = status;
	}

	public Inscription(int idInsc, int idStudent, int idCourse, int idTeacher, String commission, int partialNote,
			int finalNote, String status) {
		this.idInsc = idInsc;
		this.idStudent = idStudent;
		this.idCourse = idCourse;
		this.idTeacher = idTeacher;
		this.commission = commission;
		this.partialNote = partialNote;
		this.finalNote = finalNote;
		this.status = status;
	}

	public Inscription(int idInsc, int idStudent, int idCourse) {
		this.idInsc = idInsc;
		this.idStudent = idStudent;
		this.idCourse = idCourse;
	}

	public String getCommission() {
		return commission;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public int getPartialNote() {
		return partialNote;
	}

	public void setPartialNote(int partialNote) {
		this.partialNote = partialNote;
	}

	public int getFinalNote() {
		return finalNote;
	}

	public void setFinalNote(int finalNote) {
		this.finalNote = finalNote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getIdInsc() {
		return idInsc;
	}

	public void setIdInsc(int idInsc) {
		this.idInsc = idInsc;
	}

}
