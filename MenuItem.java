import java.util.Scanner;
import java.io.Serializable;

/**
 * Created by Ash-0224 on 4/11/16.
 */
public class MenuItem implements Serializable{
    protected String name;
    protected double price;
    protected String description;
    protected Category c;

    public enum Category {Item, MainCourse, Drink, Dessert};

    public MenuItem() {
        name = "unknown";
        price = -1;
        description = "unknow";
        c = Category.Item;
    }

    public MenuItem(String n, double p, String d) {
        name = n;
        price = p;
        description = d;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double newP) {
        price = newP;
    }

    public void setDescription(String newD) {
        description = newD;
    }

    public String toString() {
        if (c == Category.MainCourse) {
            return "MainCourse: " + name + "\nPrice:" + price + "\nDescription: " + description;
        } else if (c == Category.Drink) {
            return "Drink: " + name + "\nPrice:" + price + "\nDescription: " + description;
        } else if (c == Category.Dessert) {
            return "Dessert: " + name + "\nPrice:" + price + "\nDescription: " + description;
        } else {
            return "No such item exists.";
        }
    }

    public void update() {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Which attribute of item would you like to update?");
        System.out.println("(1). Price");
        System.out.println("(2). Description");
        System.out.println("(3). Both");
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                double p;
                System.out.println("Please insert the new price for item " + name);
                p = sc.nextDouble();
                price = p;
                System.out.println("Update successfully, now the price for item " + name + " is " + price);
                break;
            }
            case 2: {
                String d;
                sc.nextLine();
                System.out.println("Please insert the new description for item " + name);
                d = sc.nextLine();
                setDescription(d);
                System.out.println("Update successfully, now the description for item " + name + " is \n"
                        + description);
                break;
            }
            case 3: {
                double p;
                System.out.println("Please insert the new price for item " + name);
                p = sc.nextDouble();
                price = p;
                System.out.println("Update successfully, now the price for item" + name + " is " + price);
                String d;
                sc.nextLine();
                System.out.println("Please insert the new description for item " + name);
                d = sc.nextLine();
                description = d;
                System.out.println("Update successfully, now the description for item" + name + " is \n"
                        + description);
                break;
            }
            default: {
                System.out.println("Invalid choice.");
                break;
            }
        }
    }
}