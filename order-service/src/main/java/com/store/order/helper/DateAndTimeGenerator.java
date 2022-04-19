package com.store.order.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeGenerator {
	
	public static String getDateAndTime() {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now(); 
		return currentDate.format(pattern);
	}
}
