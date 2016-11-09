import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.io.*;

public class Tables implements Serializable{

	private static class Table implements Serializable{
		
		private int capacity;
		private int tableID;
		private HashSet<DateTime> booked;
		 
		public Table(int tableID, int capacity) {
			this.tableID = tableID;
			this.capacity = capacity;
			this.booked = new HashSet<DateTime>();
		}
		
		public boolean available(DateTime d){
			if (d.getMinute() == 0 && (d.getHour() == 18 || d.getHour() == 9))
				return !booked.contains(d);
			if (d.getHour() > 15)
				return !booked.contains(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 18, 0));
			else
				return !booked.contains(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 9, 0));
		}
		
		public void book(DateTime d){
			if (d.getMinute() == 0 && (d.getHour() == 18 || d.getHour() == 9))
				booked.add(d);
			else{				
				if (d.getHour() > 15)
					booked.add(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 18, 0));
				else
					booked.add(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 9, 0));
			}
		}
		
		public void remove(DateTime d){
			if (!this.available(d)){
				if (d.getHour() > 15)
					booked.remove(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 18, 0));
				else
					booked.remove(new DateTime(d.getYear(), d.getMonth(), d.getDate(), 9, 0));
			}
		}
		
	}
	
	private HashMap<Integer, Table> tables;
	
	public Tables() {
		tables = new HashMap<Integer, Table>();
		for (int i = 0; i < 5; i++){
			tables.put(1000+i+1, new Table(1000+i+1, 10));
			tables.put(800+i+1, new Table(800+i+1, 8));
		}
		for (int i = 0; i < 10; i++){
			tables.put(400+i+1, new Table(400+i+1, 4));
			tables.put(200+i+1, new Table(200+i+1, 2));
		}
	}

	public boolean available(int tableID, DateTime d) {
		if (!tables.containsKey(tableID))
			throw new NoSuchElementException("Illegal table ID");
		return tables.get(tableID).available(d);
	}
	
	public void bookTable(int tableID, DateTime d){
		if (!tables.containsKey(tableID))
			throw new NoSuchElementException("Illegal table ID");
		if (tables.get(tableID).available(d))
		    tables.get(tableID).book(d);
		else
			System.out.println("Table already booked for this period");
	}
	
	public void removeBooking(int tableID, DateTime d){
		if (!tables.containsKey(tableID))
			throw new NoSuchElementException("Illegal table ID");
		tables.get(tableID).remove(d);
	}
	
	/** 
	 * Automatically book a table with appropriate size at the specified date & time
	 * @param size   the required table size;
	 * @param d      the date time of booking
	 * @return       the booked table's ID if booking is successful, return -1 if unsuccessful 
	 */
	public int book(int pax, DateTime d){
		int tableID;
		switch(pax) {
			case 10:
			case 9:
				for (tableID = 1001; tableID <= 1005; tableID++)
					if (this.available(tableID, d)) {
						this.bookTable(tableID, d);
						return tableID;
					}
				if (tableID  == 1006)
					return -1;
				break;
			case 8:
			case 7:
			case 6:
			case 5:
				for (tableID = 801; tableID <= 805; tableID++)
					if (this.available(tableID, d)) {
						this.bookTable(tableID, d);
						return tableID;
					}
				if (tableID  == 806)
					return -1;
				break;
			case 4:
			case 3:
				for (tableID = 401; tableID <= 410; tableID++)
					if (this.available(tableID, d)) {
						this.bookTable(tableID, d);
						return tableID;
					}
				if (tableID  == 411)
					return -1;
				break;
			case 2:
			case 1:
				for (tableID = 201; tableID < 210; tableID++)
					if (this.available(tableID, d)) {
						this.bookTable(tableID, d);
						return tableID;
					}
				if (tableID  == 211)
					return -1;
				break;
			default:
				throw new NoSuchElementException("Table size");
		}
		return -1;
	}
	
	/**
	 * Test main
	 * @param args
	 */
	/*public static void main(String[] args){
		Tables ts = new Tables();
		DateTime d1 = new DateTime(2016, 10, 7, 10, 0);
		DateTime d2 = new DateTime(2016, 10, 7, 19, 0);
		
		System.out.println(ts.book(10,d2));
		
		System.out.println(ts.book(9,d2));
		
		System.out.println(ts.book(8,d2));
		
		System.out.println(ts.book(10,d2));
		
		System.out.println(ts.book(9,d2));
		ts.removeBooking(1001, d2);
		
		System.out.println(ts.book(10,d2));
		
		System.out.println(ts.book(10,d2));
		
		System.out.println(ts.book(10,d2));
		System.out.println(ts.book(3,d2));
	}*/
}
