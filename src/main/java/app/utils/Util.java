package app.utils;

import java.util.Scanner;

public class Util {

	private static final int MAX_CHAR = 50;
	private static final int MIN_CHAR = 1;
	private static final int MAX_NOTE = 10;
	private static final int MIN_NOTE = 0;

	public static String valueForNullString(String text) {
		if (text == null) {
			return "none";
		}
		return text;
	}

	public static boolean notValidStringLength(String text) {
		boolean notValid = text.length() <= MIN_CHAR || text.length() > MAX_CHAR;
		return notValid;
	}

	public static void showError(String text) {
		System.out.println(" *** " + text + " *** ");
	}

	public static void showSubtitle(String text) {
		System.out.println();
		System.out.println(text);
		System.out.println("-------------------------------------");
	}

	public static void showTitle(String text) {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println(text);
		System.out.println("-------------------------------------");
	}

	public static int requestCalifications(Scanner scan, String califType) {
		System.out.print("Ingrese nota " + califType + " -> ");
		int note = scan.nextInt();
		while (note > MAX_NOTE || note < MIN_NOTE) {
			showError("Error de ingreso. Valor inválido");
			System.out.print("Ingrese nota " + califType + " -> ");
			note = scan.nextInt();
		}
		return note;
	}

	public static String requestCommision(Scanner scan) {
		System.out.print("Ingrese comisión -> ");
		String commission = scan.next();
		while (notValidStringLength(commission)) {
			showError("Error de ingreso. Texto inválido");
			System.out.print("Ingrese comisión -> ");
			commission = scan.next();
		}
		return commission;
	}

	public static String requestSpecialty(Scanner scan) {
		System.out.print("Ingrese especialidad -> ");
		String specialty = scan.next();
		while (notValidStringLength(specialty)) {
			showError("Error de ingreso. Texto inválido");
			System.out.print("Ingrese especialidad -> ");
			specialty = scan.next();
		}
		return specialty;
	}

	public static String requestEmail(Scanner scan, String field) {
		System.out.print("Ingrese correo electrónico del " + field + " -> ");
		// Validate email
		String email = scan.next();
		while (notValidStringLength(email)) {
			showError("Error de ingreso. Texto inválido");
			System.out.print("Ingrese correo electrónico del " + field + " -> ");
			email = scan.next();
		}
		return email;
	}

	public static String requestNameInfo(Scanner scan, String option, String field) {
		System.out.print("Ingrese " + option + " del " + field + " -> ");
		String value = scan.next();
		while (notValidStringLength(value)) {
			showError("Error de ingreso. Texto inválido");
			System.out.print("Ingrese " + option + " del " + field + " -> ");
			value = scan.next();
		}
		return value;
	}

	public static int requestId(Scanner scan, String field) {
		System.out.print("Ingrese id del " + field + " -> ");
		return scan.nextInt();
	}

}
