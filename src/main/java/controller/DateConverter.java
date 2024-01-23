package controller;

import java.time.LocalDate;
import java.sql.Date;

public class DateConverter {
	public static Date convertToLocalDate(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);
	}
}


