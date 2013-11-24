package joprod.jitune.data.common;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Date {
	private final GregorianCalendar cal = new GregorianCalendar();

	public Date(java.util.Date date) {
		cal.setTime(date);
	}

	public Date(int year, int month, int day) {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
	}

	public int getYear() {	
		return cal.get(Calendar.YEAR);
	}

	public int getMonth() {
		return cal.get(Calendar.MONTH);
	}

	public int getDay() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public java.util.Date getJavaDate() {
		return cal.getTime();
	}

}
