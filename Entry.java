import java.io.Serializable;

public class Entry implements Serializable{
		private String name;
		private int quantity;
		private DateTime dateTime;
		private double price;
		
		public Entry(String name, DateTime d,  int q, double price){
			this.name = name;
			this.dateTime = d;
			this.quantity = q;
			this.price = price;
		}
		
		public String getName(){
			return this.name;
		}
		
		public DateTime getDateTime(){
			return this.dateTime;
		}
		
		public int getQuantity(){
			return this.quantity;
		}
		
		public double getPrice(){
			return this.price;
		}
		
		public void addQuantity(int n){
			this.quantity += n;
		}
		
		public void addValue(double n){
			this.price += n;
		}
}