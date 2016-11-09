import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ash-0224 on 4/11/16.
 */
public class SetPackage implements Serializable{
	
    protected String name;
    protected double price;
    protected double discountRate;
    protected ArrayList<MenuItem> set;
    protected int quantity;

    public SetPackage(String n, double r) {
        name = n;
        set = new ArrayList<MenuItem>();
        discountRate = r;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRate() {
        return discountRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice() {
        int p = 0;
        for(int i = 0; i<set.size(); i++){
            p += set.get(i).getPrice();
        }
        price = p*discountRate;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setDiscountRate(double r){
        discountRate = r;
    }

    public void addItem(MenuItem newItem) {
        set.add(newItem);
        System.out.println(newItem.getName() + " is added into set " + name + ".");
    }

    public void changeItem(int pos, MenuItem newItem) {
        //set[0]: main course
        //set[1]: drink
        //set[2]: dessert
        set.set(pos-1, newItem);
        viewSetPackage();
    }

    public void viewSetPackage() {
        System.out.println("SetPackage " + name);
        for (int i = 0; i < set.size(); i++) {
            System.out.println(set.get(i).toString());
        }
        System.out.println("Price: " + price);
        System.out.println("Discount rate: " + discountRate);
    }
}
