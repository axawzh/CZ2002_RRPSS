import java.io.Serializable;
import java.util.ArrayList;


public class Order implements Serializable {
	
	private final DateTime dateTime;
	private int tableID;
    private int employeeID;
    private int orderID;
    
	private static class OrderItem implements Serializable{
		private int quantity;
		private String name;
		private double price;
		
		public OrderItem(String name, int quantity, double price) {
			this.name = name;
			this.quantity = quantity;
			this.price = price;
		}
		
		public int getQuantity() {
			return this.quantity;
		}
		
		public double getPrice() {
			return this.price;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void addQuantity(int n) {
			this.quantity += n;
		}
		
		
	}
	
	private ArrayList<OrderItem> orderItems;
    
	public Order(DateTime dateTime, int employeeID, int tableID) {
    	orderItems = new ArrayList<OrderItem>();
    	this.dateTime = dateTime;
    	this.employeeID = employeeID;
    	this.tableID = tableID;
    	this.orderID = dateTime.hashCode();
    }

	public int getID(){
		return this.orderID;
	}
	
	public int getTableID(){
		return this.tableID;
	}
	
	public DateTime getDateTime(){
		return this.dateTime;
	}
	
	public void add(MenuItem item, int quantity) {
		for (int i = 0; i < orderItems.size(); i++)
			if (orderItems.get(i).getName().equals(item.getName())) {
				orderItems.get(i).addQuantity(quantity);
				return;
			}
		orderItems.add(new OrderItem(item.getName(), quantity, item.getPrice()));
	}
	
	public void add(SetPackage set, int quantity) {
		for (int i = 0; i < orderItems.size(); i++)
			if (orderItems.get(i).getName().equals(set.getName())) {
				orderItems.get(i).addQuantity(quantity);
				return;
			}
		orderItems.add(new OrderItem(set.getName(), quantity, set.getPrice()));
	}
	
	public void remove(MenuItem item, int quantity) {
		for (int i = 0; i < orderItems.size(); i++)
			if (orderItems.get(i).getName().equals(item.getName())) {
				orderItems.get(i).addQuantity(-quantity);
				if (orderItems.get(i).getQuantity() <= 0)
					orderItems.remove(i);
				return;
			}
	}
	
	public void remove(SetPackage set, int quantity) {
		for (int i = 0; i < orderItems.size(); i++)
			if (orderItems.get(i).getName().equals(set.getName())) {
				orderItems.get(i).addQuantity(-quantity);
				if (orderItems.get(i).getQuantity() <= 0)
					orderItems.remove(i);
				return;
			}
	}
	
	public void remove(int index, int quantity){
		orderItems.get(index).addQuantity(-quantity);
		if (orderItems.get(index).getQuantity() <= 0)
			orderItems.remove(index);
	}
	
	public void remove(int index){
		orderItems.remove(index);
	}
	
	public boolean isEmpty(){
		return orderItems.isEmpty();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		double total = 0;
		sb.append("Order Details\n");
		sb.append("ID: " + this.orderID + "\n");
		sb.append(this.dateTime);
		sb.append("Staff: " + this.employeeID + "\n");
		sb.append("-------------------------------------------\n");
		sb.append(" S/N          Items         Quantity Price\n");
		for (int i = 0; i < orderItems.size(); i++) {
			OrderItem oTemp = orderItems.get (i);
			total += oTemp.getPrice() * oTemp.getQuantity();
			sb.append(String.format("%3d  %-23s %4d   $%5.2f\n", i+1, oTemp.getName(), oTemp.getQuantity(), oTemp.getQuantity() * oTemp.getPrice()));
		}
		sb.append("-------------------------------------------\n");
		sb.append(String.format("                           Total : $%5.2f\n", total));
		return sb.toString();
	}
	
	public String invoice(){
		StringBuilder sb = new StringBuilder();
		double total = 0;
		sb.append("Invoice\n");
		sb.append("Slip No.: 0000" + this.employeeID + "" + this.orderID + "\n");
		sb.append(this.dateTime);
		sb.append("Staff: " + this.employeeID + "\n");
		sb.append("-------------------------------------------\n");
		sb.append(" S/N          Items         Quantity Price\n");
		for (int i = 0; i < orderItems.size(); i++) {
			OrderItem oTemp = orderItems.get (i);
			total += oTemp.getPrice() * oTemp.getQuantity();
			sb.append(String.format("%3d  %-23s %4d   $%5.2f\n", i+1, oTemp.getName(), oTemp.getQuantity(), oTemp.getQuantity() * oTemp.getPrice()));
		}
		sb.append("-------------------------------------------\n");
		sb.append(String.format("                       Service Fee : $%5.2f\n", total * 0.1));
		sb.append(String.format("                               GST : $%5.2f\n", total * 0.07));
		sb.append(String.format("                             Total : $%5.2f\n", total * 1.17));
		return sb.toString();
	}
	
	
	
	public void record(Records r){
		for (int i = 0; i < orderItems.size(); i++){
			OrderItem oTemp = orderItems.get(i);
			r.add(new Entry(oTemp.getName(), this.dateTime, oTemp.quantity, oTemp.quantity * oTemp.price));
		}
	}
	/**
	 * Test main
	 * @param args
	 */
	public static void main(String[] args) {
		Order order = new Order(new DateTime(2016, 11, 6), 13, 13);
		order.add(new MenuItem("Chips",  1.7,"tasty"), 10);
		order.add(new MenuItem("Pork Chop 'N' Sausage", 3.5, "Cheap"), 1);
		System.out.println(order);
		order.remove(new MenuItem("Chips",  1.7,"tasty"), 5);
		System.out.println(order);
	}
}
