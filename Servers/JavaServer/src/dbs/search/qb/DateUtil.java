
package dbs.search.qb;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;


public class DateUtil {
	private final static String FORMAT = "yyyy-MM-dd HH:mm:ss z";
	
	public static String getDatePattern(String dateStr) {
		int lenDate = dateStr.length();
		int lenFormat = DateUtil.FORMAT.length();
		if(lenDate > lenFormat) return DateUtil.FORMAT;
		return DateUtil.FORMAT.substring(0, lenDate);
	}

	public static int getKeyToIncr(String dateStr) {
		int lenDate = dateStr.length();
		if(lenDate <= 5) return Calendar.YEAR;
		if(lenDate <= 8) return Calendar.MONTH;
		if(lenDate <= 11) return Calendar.DAY_OF_MONTH;
		if(lenDate <= 14) return Calendar.HOUR_OF_DAY;
		if(lenDate <= 17) return Calendar.MINUTE;
		if(lenDate <= 20) return Calendar.SECOND;
		return -1;

	}
	
	public static Calendar getNextDate(Calendar calendar, String dateStr) throws Exception {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		int keyToIncr = getKeyToIncr(dateStr);
		switch(keyToIncr) {
			case(Calendar.YEAR):
				calendar.set(Calendar.YEAR, ++year);
				break;
			case(Calendar.MONTH):
				calendar.set(Calendar.MONTH, ++month);
				break;
			case(Calendar.DAY_OF_MONTH):
				calendar.set(Calendar.DAY_OF_MONTH, ++day);
				break;
			case(Calendar.HOUR_OF_DAY):
				calendar.set(Calendar.HOUR_OF_DAY, ++hour);
				break;
			case(Calendar.MINUTE):
				calendar.set(Calendar.MINUTE, ++minute);
				break;
			case(Calendar.SECOND):
				calendar.set(Calendar.SECOND, ++second);
				break;
			default:
				break;
	
		}
		return calendar;
	}
	
	public static String epoch2DateStr(String epochStr) throws Exception {
		return (new SimpleDateFormat(DateUtil.FORMAT)).format(new Date(Long.valueOf(epochStr)));
	}

	public static long dateStr2Epoch(String dateStr) throws Exception {
		String pattern = DateUtil.getDatePattern(dateStr);
		return (new SimpleDateFormat(pattern)).parse(dateStr, new ParsePosition(0)).getTime() ;
	}

	public static Date getNextDate(String dateStr) throws Exception {
		Calendar calendar = new GregorianCalendar();
	       	calendar.setTime(new Date(DateUtil.dateStr2Epoch(dateStr)));
		//System.out.println(calendar.getTime() + "  epoch is " + DateUtil.dateStr2Epoch(dateStr));
		calendar = getNextDate(calendar, dateStr);
		//System.out.println(calendar.getTime()  + "  epoch is " + calendar.getTime().getTime() );
		return calendar.getTime();
	}
	
	
	public static void main(String args[]){
	
	
		try{
			String epoch = "1195123429000";
			System.out.println("epoch is " + epoch + " Our Date " + DateUtil.epoch2DateStr(epoch));
	
			/*
			String dStr = "2008-02-14 10:24";
			Date d = DateUtil.getNextDate(dStr);
			String epoch1 = String.valueOf(DateUtil.dateStr2Epoch(dStr));
			String epoch2 = String.valueOf(d.getTime());
			System.out.println("Epoch 1  " + epoch1);
			System.out.println("Epoch 2  " + epoch2);*/
			
			/*
			//System.out.println("ERA: " + calendar.get(Calendar.ERA));
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);

			System.out.println("YEAR: " + year);
			System.out.println("MONTH: " + month);
			//System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
			//System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
			//System.out.println("DATE: " + calendar.get(Calendar.DATE));
			System.out.println("DAY_OF_MONTH: " + day);
			//System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
			//System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
			//System.out.println("DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
			//System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
			//System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
			System.out.println("HOUR_OF_DAY: " + hour);
			System.out.println("MINUTE: " + minute);
			System.out.println("SECOND: " + second);
			//System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
			System.out.println("ZONE_OFFSET: " + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
			System.out.println("old k " + k);
			int keyToIncr = getKeyToIncr(dStr);
			switch(keyToIncr) {
				case(Calendar.YEAR):
					calendar.set(Calendar.YEAR, ++year);
					break;
				case(Calendar.MONTH):
					calendar.set(Calendar.MONTH, ++month);
					break;
				case(Calendar.DAY_OF_MONTH):
					calendar.set(Calendar.DAY_OF_MONTH, ++day);
					break;
				case(Calendar.HOUR_OF_DAY):
					calendar.set(Calendar.HOUR_OF_DAY, ++hour);
					break;
				case(Calendar.MINUTE):
					calendar.set(Calendar.MINUTE, ++minute);
					break;
				case(Calendar.SECOND):
					calendar.set(Calendar.SECOND, ++second);
					break;
				default:
					break;
	
			}
			System.out.println("new k " + calendar.getTime());
			



			
			//System.out.println("DST_OFFSET: "  + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
			
			//pstmt.execute();*/
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
