import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Reservations implements Serializable {
	
	private static class Reservation implements Serializable{
		
		private int contactNo;
		private DateTime dateTime;
		private int noOfPax;
		private int tableID;
		
		public Reservation(int contactNo, DateTime dateTime, int noOfPax, int tableID) {
			this.contactNo = contactNo;
			this.dateTime = new DateTime(dateTime);
			this.noOfPax = noOfPax;
			this.tableID = tableID;
		}
		
		public DateTime getDateTime() {
			return new DateTime(this.dateTime);
		}
		
		public int getNoOfPax() {
			return this.noOfPax;
		}
		
		public int getTableID() {
			return this.tableID;
		}
		
		public int getContactNo() {
			return this.contactNo;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Reservation");
			sb.append("\nContact No.: " + this.contactNo);
			sb.append("\nArrival Date & Time: " + this.dateTime);
			sb.append("\nNo. of Pax: " + this.noOfPax);
			sb.append("\nAssigned table:" + this.tableID + "\n");
			return sb.toString();
		}
	}
	
	private LinkedList<Reservation> reservationPQ;
	transient private Tables tables;
	
	public Reservations(Tables tables) {
		 this.tables = tables;
		 this.reservationPQ = new LinkedList<Reservation>();
	}
	
	public void setTables(Tables tables) {
		this.tables = tables;
	}
	
	public boolean add(int contactNo, DateTime dateTime, int noOfPax) {
		Calendar cal = Calendar.getInstance();
		if (!dateTime.after(cal)) {
			System.out.println("<Reserve failure> Illegal Date & Time");
			return false;
		}
		if (noOfPax > 10) {
			System.out.println("<Reserve failure> No table available");
			return false;
		}
			
		int tableID = tables.book(noOfPax, dateTime);
		if (tableID == -1) {
			System.out.println("<Reserve failure> No table available");
			return false;
		}
		if (reservationPQ.isEmpty())
			reservationPQ.add(new Reservation(contactNo, dateTime, noOfPax, tableID));
		else{
			int index = 0;
			Iterator<Reservation> itr = reservationPQ.iterator();
			while(itr.hasNext() && !itr.next().getDateTime().after(dateTime))
				index++;
			reservationPQ.add(index, new Reservation(contactNo, dateTime, noOfPax, tableID));
		}
		return true;
	}
	
	public Reservation getReservation(int contactNo) {
		this.removeExpiredReservations();
		Iterator<Reservation> itr = reservationPQ.iterator();
		while (itr.hasNext()){
			Reservation r = itr.next();
			if (r.getContactNo() == contactNo)
				return r;
		}
		return null;
	}
	
	public void removeReservation(int contactNo) {
		int index = 0;
		while(this.reservationPQ.get(index).getContactNo() != contactNo){
			index++;
			if (index == reservationPQ.size())
				return;
		}
		Reservation r = reservationPQ.get(index);
		this.tables.removeBooking(r.getTableID(), r.getDateTime());
		reservationPQ.remove(index);
	}
	
	public void printAllReservations() {
		Iterator<Reservation> itr = reservationPQ.iterator();
		int no = 1;
		while(itr.hasNext()){
			System.out.println("No. " + no);
			System.out.println(itr.next());
			no++;
		}
	}
	
	public void removeExpiredReservations() {
		Calendar cal = Calendar.getInstance();
		Calendar calTemp = (Calendar) cal.clone();
		calTemp.add(Calendar.MINUTE, +30);  //change for debugging
		while (true) {
			Reservation ptr = this.reservationPQ.getFirst();
			if (!ptr.getDateTime().after(calTemp)){
				this.tables.removeBooking(ptr.tableID, ptr.dateTime);
				this.reservationPQ.removeFirst();
			}
			else
				break;
		}
	}
	
	
	/**
	 * Test main
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tables tables = null;
		Reservations r = null;
		
		FileInputStream fin;
		ObjectInputStream oin;
		FileOutputStream fout;
		ObjectOutputStream oout;
		
		try {
			 fin = new FileInputStream("tables.ser");
			 oin = new ObjectInputStream(fin);
			 tables = (Tables) oin.readObject();
			 oin.close();
			 fin.close();
		 } catch (FileNotFoundException ex) {
			 tables = new Tables();
		 } catch (IOException ex){
			 ex.printStackTrace();
		 } catch (ClassNotFoundException ex) {
			 ex.printStackTrace();
		 }
	    try {
			 fin = new FileInputStream("reservations.ser");
			 oin = new ObjectInputStream(fin);
			 r = (Reservations) oin.readObject();
			 if (r == null)
				 r = new Reservations(tables);
			 else
				 r.setTables(tables);
			 oin.close();
			 fin.close();
		 } catch (FileNotFoundException ex) {
			 r = new Reservations(tables);
		 } catch (IOException ex) {
			 ex.printStackTrace();
		 } catch (ClassNotFoundException ex) {
			 ex.printStackTrace();
		 }
		
	
		r.add(129, new DateTime(2016,10,17,10,0), 8);
		r.add(127, new DateTime(2016,11,7,11,0), 8);
		r.add(124, new DateTime(2016,11,7,11,0), 3);
		r.printAllReservations();
		//r.removeReservation(127);
		//r.removeExpiredReservations();
		//r.printAllReservations();
		//r.removeReservation(124);
		//r.printAllReservations();
		System.out.println(r.getReservation(126));
		
		try {
			fout = new FileOutputStream("tables.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(tables);
			oout.close();
			fout.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
		try {
			fout = new FileOutputStream("reservations.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(r);
			oout.close();
			fout.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
