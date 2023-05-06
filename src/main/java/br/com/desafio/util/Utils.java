package br.com.desafio.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

	private Utils() {

	}

	public static String formatarData(LocalDate localDate) {
		if (null == localDate) {
			return "";
		}
		return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

}
