package com.benchmark.psma.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	private static long DATE_OFFSET = 15*1000*60;
	
	public static java.util.Date getDateFromTimeAndDateStrings(String time, String date) throws Exception
	{
//		String[] timeArray = time.split(":");
//		String[] dateArray = date.split("-");
		Calendar calendar = Calendar.getInstance();
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		java.util.Date formatedDate = formatter.parse(date + " " + time);
		calendar.setTime(formatedDate);
//		calendar.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) - 1
//				, Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
		return calendar.getTime();
	}
	
	public static java.util.Date getDateFromTimeAndDateStringsPlusWeeks(java.util.Date  date, int weeks)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, weeks * 7);
		return calendar.getTime();
	}
	
	public static java.util.Date getDateFromString(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.parse(date);
	}
	
	public static String getStringFromDateWithFormat(java.util.Date date)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String reportDate = df.format(date);
		return reportDate;
	}
	
	public static java.util.Date getStartSearchDate(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return  calendar.getTime();
	}
	
	public static java.util.Date getEndSearchDate(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		return  calendar.getTime();
	}
	
	public static java.util.Date getDateFromStringWithoutTime(String date) throws Exception
	{
		if(date== null || "".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.parse(date);
	}
	
	public static java.util.Date now()
	{
		return new java.util.Date();
	}
	public static String getReportName()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return "Report-" + formatter.format(new java.util.Date());
	}
	
	public static String getNowAsString()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(new java.util.Date());
	}
	
	public static String getCurrentDateString()
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return  formatter.format(new java.util.Date());
	}
	public static java.util.Date getSimpleDateFromString(String date) throws Exception
	{
		if(StringUtils.isEmpty(date) || "null".equals(date) || "NULL".equals(date))
			return null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return  formatter.parse(date);
	}
	public static String getMySqlDateString(java.util.Date date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return  formatter.format(date);
	}
	
	public static String getNowMySqlDateString()
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return  formatter.format(new java.util.Date());
	}
	
	public static String getDateStringForTimeSheet(java.util.Date date)
	{
		DateFormat formatter = new SimpleDateFormat("dd-MMM");
		return  formatter.format(date);
	}
	public static List<String> getDateDatesString(List<Date> dates){
		List<String> datesString = new ArrayList<String>();
		for(Date date : dates){
			datesString.add(DateUtil.getDateStringForTimeSheet(date));
		}
		return datesString;
	}
	
	public static List<String> getDatesList(java.util.Date fromDate, java.util.Date toDate){
		List<String> dates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		dates.add(getDateStringForTimeSheet(cal.getTime()));
		while (cal.getTime().before(toDate)) {
		    cal.add(Calendar.DATE, 1);
		    dates.add(getDateStringForTimeSheet(cal.getTime()));
		}
		return dates;
	}

}
