import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class OrderManager{
	public static void main(String[] args){
		
		FileInputStream fin;
		ObjectInputStream oin;
		FileOutputStream fout;
		ObjectOutputStream oout;
		
		int choice;
		Menu menu = null;
		ArrayList<Order> orders = null;
		Scanner sc = new Scanner(System.in);
		Tables tables = null;
		Order order = null;
		Records records = null;
		
		try {
			 fin = new FileInputStream("menu.ser");
			 oin = new ObjectInputStream(fin);
			 menu = (Menu) oin.readObject();
			 oin.close();
			 fin.close();
		 } catch (FileNotFoundException ex) {
			 menu = new Menu();
		 } catch (IOException ex){
			 ex.printStackTrace();
		 } catch (ClassNotFoundException ex) {
			 ex.printStackTrace();
		 }
		
		try {
			 fin = new FileInputStream("records.ser");
			 oin = new ObjectInputStream(fin);
			 records = (Records) oin.readObject();
			 oin.close();
			 fin.close();
		 } catch (FileNotFoundException ex) {
			 records = new Records();
		 } catch (IOException ex){
			 ex.printStackTrace();
		 } catch (ClassNotFoundException ex) {
			 ex.printStackTrace();
		 }
		
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
			 fin = new FileInputStream("orders.ser");
			 oin = new ObjectInputStream(fin);
			 orders = (ArrayList) oin.readObject();
			 oin.close();
			 fin.close();
		 } catch (FileNotFoundException ex) {
			 orders = new ArrayList<Order>();
		 } catch (IOException ex){
			 ex.printStackTrace();
		 } catch (ClassNotFoundException ex) {
			 ex.printStackTrace();
		 }
		
		System.out.println("Ordering system initiating...");
		Outer:
		while (true){
			System.out.println("(1). Start new order");
            System.out.println("(2). Add items to the order");
            System.out.println("(3). Remove items from the order");
            System.out.println("(4). View the order");
            System.out.println("(5). Send the order");
            System.out.println("(6). Close the order");
            System.out.println("(7). View all orders");
            System.out.println("(8). Quit");
            
            choice = sc.nextInt();
            int tableID;
            switch(choice){
	            case 1: {
	            	System.out.println("Enter the employee ID >>");
	            	int employeeID = sc.nextInt();
	            	System.out.println("Enter pax No. >>");
	            	int paxNo = sc.nextInt();
	            	Calendar cal = Calendar.getInstance();
	            	DateTime dateTime = new DateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
	            	tableID = tables.book(paxNo, dateTime);
	            	if (tableID == -1) {            		
	            		System.out.println("No table available");
	            		break Outer;
	            	}
	            	order = new Order(dateTime, employeeID, tableID);
	            	System.out.println("New order created.");
	            	break;
	            }
	            case 2:{
	            	int category;
	            	int index;
	            	int quantity;
	            	System.out.println("Choose the category:");
	                System.out.println("(1). MainCourse");
	                System.out.println("(2). Drink");
	                System.out.println("(3). Dessert");
	                System.out.println("(4). Set Package");
	                System.out.println("(5). Back");
	                category = sc.nextInt();
	                switch(category) {
		                case 1:
		                	menu.listMC();
		                	System.out.println("Which main course would you like to order?");
	                        index = sc.nextInt();
	                        if (index > menu.getNumMC() || index < 1)
	                            System.out.println("Main course does not exist.");
	                        else {
	                        	System.out.println("How many do you want to order?");
	                            quantity = sc.nextInt();
	                            order.add(menu.getMC(index), quantity);   
	                        }
	                        break;
		                case 2:
		                	menu.listDr();
		                	System.out.println("Which drink would you like to have");
	                        index = sc.nextInt();
	                        if (index > menu.getNumDr() || index < 1)
	                            System.out.println("That drink does not exist.");
	                        else {
	                        	System.out.println("How many do you want to order?");
	                            quantity = sc.nextInt();
	                            order.add(menu.getDrink(index), quantity);   
	                        }
	                        break;
			            case 3:
		                	menu.listDe();
		                	System.out.println("Which dessert would you like to have");
		                    index = sc.nextInt();
		                    if (index > menu.getNumDe() || index < 1)
		                        System.out.println("That dessert does not exist.");
		                    else {
		                    	System.out.println("How many do you want to order?");
		                        quantity = sc.nextInt();
		                        order.add(menu.getDessert(index), quantity);   
		                    }
		                    break;
			            case 4:
		                	menu.listSet();
		                	System.out.println("Which set package would you like to order");
		                    index = sc.nextInt();
		                    if (index > menu.getSetNum() || index < 1)
		                        System.out.println("That set does not exist.");
		                    else {
		                    	System.out.println("How many do you want to order?");
		                        quantity = sc.nextInt();
		                        order.add(menu.getSet(index), quantity);
		                        System.out.println("Items successfully added.");
		                    }
		                    break;
		                default:
		                	break;
	                }
	                break;
                }
	            	
	            case 3:{
	            	int index;
	            	int quantity;
				    System.out.println(order);
				    System.out.println("Enter the no. of the item you want to remove>>");
				    index = sc.nextInt();
				    order.remove(index-1);
				    System.out.println("Item removed");
	            }
	            case 4:
	            	if (order != null)
	            		System.out.println(order);
	            	break;
	            case 5:
	            	if (order.isEmpty())
	            		System.out.println("The order is empty");
	            	else{
	            		orders.add(order);
	            		order.record(records);
	            		System.out.println("The order has been sent to the kitchen");
	            	}
	            	break;
	            case 6:
	            	for (int index = 0; index < orders.size(); index++)
	        			System.out.println(orders.get(index));
	            	System.out.println("Enter the ID of the order to be closed");
	            	int orderID;
	            	orderID = sc.nextInt();
	            	for (int index = 0; index < orders.size(); index++)
	            		if (orders.get(index).getID() == orderID){
	            			System.out.println(orders.get(index).invoice());
	            			System.out.println("Payment successful");
	            			tables.removeBooking(orders.get(index).getTableID(), orders.get(index).getDateTime());
	            			orders.remove(index);
	            			System.out.println("Order closed"); 
	            		}
	            	break;
	            case 7:
	            	for (int index = 0; index < orders.size(); index++)
	        			System.out.println(orders.get(index));
	            	break;
	            case 8:
	            	break Outer;
	            default:
	            	break;
            }
		}
		
		try {
			fout = new FileOutputStream("menu.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(menu);
			oout.close();
			fout.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			fout = new FileOutputStream("orders.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(orders);
			oout.close();
			fout.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			fout = new FileOutputStream("records.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(records);
			oout.close();
			fout.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			fout = new FileOutputStream("tables.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(tables);
			oout.close();
			fout.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
        System.out.println("Program Terminating...");
	}	
	
}
