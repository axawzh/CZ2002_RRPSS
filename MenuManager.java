import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Created by Ash-0224 on 5/11/16.
 */
public class MenuManager {
    public static void main(String[] args) {
    	FileInputStream fin;
		ObjectInputStream oin;
		FileOutputStream fout;
		ObjectOutputStream oout;
		Menu menu = null;
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
        
        int choice;
        String name;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMenu Manager");
        do {
            System.out.println("(1). Add menu item");
            System.out.println("(2). Delete menu item");
            System.out.println("(3). Update menu item");
            System.out.println("(4). Add set package");
            System.out.println("(5). Delete set package");
            System.out.println("(6). Update set package");
            System.out.println("(7). View menu item");
            System.out.println("(8). View set package");
            System.out.println("(9). Quit");
            choice = sc.nextInt();
            switch (choice) {
                //add menu item
                case 1: {
                    int category;
                    //n for name, d for description
                    String n, d;
                    //p for price, r for discount rate
                    double p, r;
                    System.out.println("Choose the category of the new item?");
                    System.out.println("(1). MainCourse");
                    System.out.println("(2). Drink");
                    System.out.println("(3). Dessert");
                    category = sc.nextInt();
                    switch (category) {
                        case 1: {
                            sc.nextLine();
                            System.out.println("Input the NAME of the new main course: ");
                            n = sc.nextLine();
                            System.out.println("Input the PRICE for new main course: ");
                            p = sc.nextDouble();
                            sc.nextLine();
                            System.out.println("Input the DESCRIPTION for new main course: ");
                            d = sc.nextLine();
                            MenuItem item = new MainCourse(n, p, d);
                            menu.addItem(item);
                            System.out.println("New main course " + item.getName() + " is added.");
                            break;
                        }
                        case 2: {
                            sc.nextLine();
                            System.out.println("Input the NAME of the new drink: ");
                            n = sc.nextLine();
                            System.out.println("Input the PRICE for new drink: ");
                            p = sc.nextDouble();
                            sc.nextLine();
                            System.out.println("Input the DESCRIPTION for new drink: ");
                            d = sc.nextLine();
                            MenuItem item = new Drink(n, p, d);
                            menu.addItem(item);
                            System.out.println("New drink " + item.getName() + " is added.");
                            break;
                        }
                        case 3: {
                            sc.nextLine();
                            System.out.println("Input the NAME of the new dessert: ");
                            n = sc.nextLine();
                            System.out.println("Input the PRICE for new dessert: ");
                            p = sc.nextDouble();
                            sc.nextLine();
                            System.out.println("Input the DESCRIPTION for new dessert: ");
                            d = sc.nextLine();
                            MenuItem item = new Dessert(n, p, d);
                            menu.addItem(item);
                            System.out.println("New dessert " + item.getName() + " is added.");
                            break;
                        }
                        default: {
                            System.out.println("Invalid category.");
                            break;
                        }
                    }
                    break;
                }
                //delete menu item
                case 2: {
                    int c, pos;
                    menu.listItem();
                    System.out.println("Which category of menu item would you like to delete?");
                    System.out.println("(1). Main Course");
                    System.out.println("(2). Drink");
                    System.out.println("(3). Dessert");
                    c = sc.nextInt();
                    switch (c) {
                        case 1: {
                            System.out.println("Which main course would you like to delete?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumMC())
                                System.out.println("Main course does not exist.");
                            else {
                                name = menu.removeMC(pos);
                                System.out.println("Main course " + name + " is deleted.");
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Which drink would you like to delete?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumDr())
                                System.out.println("Drink does not exist.");
                            else {
                                name = menu.removeDr(pos);
                                System.out.println("Drink " + name + " is deleted.");
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("Which dessert would you like to delete?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumDe())
                                System.out.println("Dessert does not exist.");
                            else {
                                name = menu.removeDe(pos);
                                System.out.println("Dessert " + name + " is deleted.");
                            }
                            break;
                        }
                        default: {
                            System.out.println("Invalid input.");
                            break;
                        }
                    }
                    break;
                }
                //update menu item
                case 3: {
                    int c, pos;
                    menu.listItem();
                    System.out.println("Which category of menu item would you like to update?");
                    System.out.println("(1). Main Course");
                    System.out.println("(2). Drink");
                    System.out.println("(3). Dessert");
                    c = sc.nextInt();
                    switch (c) {
                        case 1: {
                            System.out.println("Which main course item would you like to update?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumMC())
                                System.out.println("Main course does not exist.");
                            else {
                                menu.updateMC(pos);
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Which drink item would you like to update?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumDr())
                                System.out.println("Drink does not exist.");
                            else
                                menu.updateDr(pos);
                            break;
                        }
                        case 3: {
                            System.out.println("Which dessert item would you like to update?");
                            pos = sc.nextInt();
                            if (pos > menu.getNumDe())
                                System.out.println("Dessert does not exist.");
                            else
                                menu.updateDe(pos);
                            break;
                        }
                        default: {
                            System.out.println("Category does not exist.");
                            break;
                        }
                    }
                    break;
                }
                //add set package
                case 4: {
                    String n;
                    double r;
                    int pos;
                    MenuItem item;
                    sc.nextLine();
                    System.out.println("Input the NAME of the new set: ");
                    n = sc.nextLine();
                    System.out.println("Input the DISCOUNT RATE for the new set: ");
                    r = sc.nextDouble();
                    SetPackage set = new SetPackage(n, r);
                    menu.listMC();
                    System.out.println("Which main course would you like to add?");
                    pos = sc.nextInt();
                    if (pos > menu.getNumMC())
                        System.out.println("Main course does not exist.");
                    else {
                        item = menu.getMC(pos);
                        set.addItem(item);
                    }
                    menu.listDr();
                    System.out.println("Which drink would you like to add?");
                    pos = sc.nextInt();
                    if (pos > menu.getNumDr())
                        System.out.println("Drink does not exist.");
                    else {
                        item = menu.getDrink(pos);
                        set.addItem(item);
                    }
                    menu.listDe();
                    System.out.println("Which dessert would you like to add?");
                    pos = sc.nextInt();
                    if (pos > menu.getNumDe())
                        System.out.println("Dessert does not exist.");
                    else {
                        item = menu.getDessert(pos);
                        set.addItem(item);
                    }
                    set.setPrice();
                    menu.addSet(set);
                    break;
                }
                //delete set package
                case 5: {
                    int pos;
                    menu.listSet();
                    System.out.println("Which set package would you like to delete?");
                    pos = sc.nextInt();
                    if (pos > menu.getSetNum())
                        System.out.println("Set package does not exist.");
                    else
                        menu.deleteSet(pos);
                    break;
                }
                //update set package
                case 6: {
                    int c, posS, posM;
                    menu.listSet();
                    SetPackage set;
                    System.out.println("\nWhich set package would you like to update?");
                    posS = sc.nextInt();
                    if (posS > menu.getSetNum())
                        System.out.println("Set package does not exist.");
                    else {
                        set = menu.getSet(posS);
                        System.out.println("How would you like to update the set?");
                        System.out.println("(1). Change item");
                        System.out.println("(2). Change Discount rate");
                        c = sc.nextInt();
                        switch (c) {
                            case 1: {
                                MenuItem item;
                                System.out.println("\nWhich item would you like to change?");
                                System.out.println("(1). Main Course");
                                System.out.println("(2). Drink");
                                System.out.println("(3). Dessert");
                                c = sc.nextInt();
                                switch (c) {
                                    case 1: {
                                        menu.listMC();
                                        System.out.println("Change with which main course?");
                                        posM = sc.nextInt();
                                        if (posM > menu.getNumMC()) {
                                            System.out.println("Main course does not exist.");
                                            break;
                                        }
                                        else {
                                            item = menu.getMC(posM);
                                            menu.updateSetItem(posS, c, item);
                                        }
                                        break;
                                    }
                                    case 2: {
                                        menu.listDr();
                                        System.out.println("Change with which drink?");
                                        posM = sc.nextInt();
                                        if (posM > menu.getNumDr()) {
                                            System.out.println("Drink does not exist.");
                                            break;
                                        }
                                        else {
                                            item = menu.getDrink(posM);
                                            menu.updateSetItem(posS, c, item);
                                        }
                                        break;
                                    }
                                    case 3: {
                                        menu.listDe();
                                        System.out.println("Change with which dessert?");
                                        posM = sc.nextInt();
                                        if (posM > menu.getNumDe()) {
                                            System.out.println("Dessert does not exist.");
                                            break;
                                        }
                                        else {
                                            item = menu.getDessert(posM);
                                            menu.updateSetItem(posS, c, item);
                                        }
                                        break;
                                    }
                                    default: {
                                        System.out.println("Item does not exist.");
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2:{
                                double r;
                                System.out.println("Input the new discount rate: ");
                                r = sc.nextDouble();
                                menu.updateSetRate(posS, r);
                            }
                        }
                    }
                    break;
                }
                //view menu item
                case 7: {
                    menu.viewItem();
                    break;
                }
                case 8: {
                    menu.viewSet();
                    break;
                }
            }
        } while (choice != 9);
        
        try {
			fout = new FileOutputStream("menu.ser");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(menu);
			oout.close();
			fout.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
        System.out.println("Program Terminating...");
    }
}
