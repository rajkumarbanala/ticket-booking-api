package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

public class DateUtil {
	
	public static String convertToMysqlFromat(LocalDateTime date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return date.format(dateTimeFormatter);
	}
	public static String convertToGlobalFromat(LocalDateTime date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return date.format(dateTimeFormatter);
	}
}
