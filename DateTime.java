import java.util.Calendar;
import java.util.HashSet;
import java.io.*;

public class DateTime implements Comparable<DateTime>, Serializable {
	
	private final int year;
	private final int month;
	private final int date;
	private final int hour;
	private final int minute;
	private final int signature;
	
	public DateTime(int year, int month, int date, int hour, int minute) {
		if (year < 2000 || year > 2050 || month < 0 || month > 11 || date < 1 || date > 31 || hour < 0 || hour > 23 || minute < 0 || minute > 59)
			throw new IllegalArgumentException("Illegal Argument: DateTime");
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.signature = ((this.year-2000) << 20) + (this.month << 16) + (this.date << 11) + (this.hour << 6) + (this.minute);
	}
	
	public DateTime(int year, int month, int date) {
		this(year, month, date, 0, 0);
	}
	
	public DateTime(DateTime that) {
		this(that.year, that.month, that.date, that.hour, that.minute);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DateTime)) 
			return false;
		DateTime that = (DateTime) o;
		if (this.signature == that.signature)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode(){
		return signature;
	}
	
	public int compareTo(DateTime that){
		if (this.signature < that.signature)
			return -1;
		else if (this.signature > that.signature)
			return 1;
		else 
			return 0;
	}
	
	public boolean after(DateTime that) {
		return this.compareTo(that) == 1;
	}
	
	public boolean after(Calendar cal) {
		int cYear = cal.get(Calendar.YEAR);
		int cMonth = cal.get(Calendar.MONTH);
		int cDate = cal.get(Calendar.DATE);
		int cHour = cal.get(Calendar.HOUR_OF_DAY);
		int cMinute = cal.get(Calendar.MINUTE);
		if (cYear < 2000)
			return true;
		else{
			int cSignature = ((cYear-2000) << 20) + (cMonth << 16) + (cDate << 11) + (cHour << 6) + (cMinute);
			if (cSignature < this.signature)
				return true;
			else 
				return false;
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		switch(this.month){
			case 11:
				sb.append("Dec ");
				break;
			case 10:
				sb.append("Nov ");
				break;
			case 9:
				sb.append("Oct ");
				break;
			case 8:
				sb.append("Sep ");
				break;
			case 7:
				sb.append("Aug ");
				break;
			case 6:
				sb.append("Jul ");
				break;
			case 5:
				sb.append("Jun ");
				break;
			case 4:
				sb.append("May ");
				break;
			case 3:
				sb.append("Apr ");
				break;
			case 2:
				sb.append("Mar ");
				break;
			case 1:
				sb.append("Feb ");
				break;
			case 0:
				sb.append("Jan ");
				break;
			default:
				break;
		}
		sb.append(this.date + " ");
		sb.append(this.hour + ":");
		if (this.minute < 10)
			sb.append("0");
		sb.append(this.minute + " ");
		sb.append("CST ");
		sb.append(this.year + " ");
		return sb.toString();
	}
	
	public int getYear(){
		return year;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getDate(){
		return date;
	}
	
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	/**
	 * Test main
	 * @param args
	 */
	public static void main(String[] args){
		DateTime d1 = new DateTime(2016, 10, 7, 23, 20);
		DateTime d2 = new DateTime(2016, 0, 7, 23, 20);
		System.out.println(d1.compareTo(d2));
		Calendar cal = Calendar.getInstance();
		Calendar calTemp = (Calendar) cal.clone();
		calTemp.add(Calendar.MINUTE, -30);
		System.out.println(d1.after(calTemp));
		
		HashSet<DateTime> booked = new HashSet();
		System.out.println(booked.contains(d1));
		booked.add(d2);
		System.out.println(booked.contains(d1));
		
		System.out.println(d2);
	}
}
