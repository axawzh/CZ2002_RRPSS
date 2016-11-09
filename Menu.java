import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by Ash-0224 on 4/11/16.
 */
public class Menu implements Serializable{
    private ArrayList<MenuItem> mainCourse;
    private ArrayList<MenuItem> drink;
    private ArrayList<MenuItem> dessert;
    private ArrayList<SetPackage> setPackages;

    public Menu() {
        mainCourse = new ArrayList<MenuItem>();
        drink = new ArrayList<MenuItem>();
        dessert = new ArrayList<MenuItem>();
        setPackages = new ArrayList<SetPackage>();
    }

    public int getNumMC() {
        return mainCourse.size();
    }

    public int getNumDr() {
        return drink.size();
    }

    public int getNumDe() {
        return dessert.size();
    }

    public MenuItem getMC(int pos) {
        return mainCourse.get(pos - 1);
    }

    public MenuItem getDrink(int pos) {
        return drink.get(pos - 1);
    }

    public MenuItem getDessert(int pos) {
        return dessert.get(pos - 1);
    }

    public SetPackage getSet(int pos) {
        return setPackages.get(pos - 1);
    }

    public void listMC(){
        System.out.println("Main Course: ");
        for (int i = 0; i < mainCourse.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(mainCourse.get(i).getName());
        }
    }

    public void listDr(){
        System.out.println("Drink: ");
        for (int i = 0; i < drink.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(drink.get(i).getName());
        }
    }

    public void listDe() {
        System.out.println("Dessert: ");
        for (int i = 0; i < dessert.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(dessert.get(i).getName());
        }
    }

    public void listItem(){
        listMC();
        listDr();
        listDe();
    }

    public void viewItem() {
        System.out.println("Ala Carte Menu ");
        System.out.println("Main Course: ");
        for (int i = 0; i < mainCourse.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(mainCourse.get(i).toString());
        }
        System.out.println("Drink: ");
        for (int i = 0; i < drink.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(drink.get(i).toString());
        }
        System.out.println("Dessert: ");
        for (int i = 0; i < dessert.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(dessert.get(i).toString());
        }
    }

    public void listSet() {
        System.out.println("Set Package: ");
        for (int i = 0; i < setPackages.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            System.out.println(setPackages.get(i).getName());
        }
    }

    public void viewSet() {
        System.out.println("Set Packages: ");
        for (int i = 0; i < setPackages.size(); i++) {
            System.out.print("(" + (i + 1) + "). ");
            setPackages.get(i).viewSetPackage();
        }
    }

    //return the number of set package in the menu
    public int getSetNum() {
        return setPackages.size();
    }

    public void addItem(MenuItem newItem) {
        if (newItem.c == MenuItem.Category.MainCourse)
            mainCourse.add(newItem);
        else if (newItem.c == MenuItem.Category.Drink)
            drink.add(newItem);
        else if (newItem.c == MenuItem.Category.Dessert)
            dessert.add(newItem);
        else
            System.out.println("Invalid menu item.");
    }

    public String removeMC(int pos) {
        String name = mainCourse.get(pos - 1).name;
        mainCourse.remove(pos - 1);
        return name;
    }

    public String removeDr(int pos) {
        String name = drink.get(pos - 1).name;
        drink.remove(pos - 1);
        return name;
    }

    public String removeDe(int pos) {
        String name = dessert.get(pos - 1).name;
        dessert.remove(pos - 1);
        return name;
    }

    public void updateMC(int pos) {
        mainCourse.get(pos - 1).update();
    }

    public void updateDr(int pos) {
        drink.get(pos - 1).update();
    }

    public void updateDe(int pos) {
        dessert.get(pos - 1).update();
    }

    public void addSet(SetPackage s) {
        setPackages.add(s);
        System.out.println("Set package " + s.getName() + " is added.");
        s.viewSetPackage();
    }

    public void deleteSet(int pos) {
        String name = setPackages.get(pos - 1).getName();
        setPackages.remove(pos - 1);
        System.out.println("Set package " + name + " is deleted.");
    }

    public void updateSetItem(int pos, int c, MenuItem newItem) {
        setPackages.get(pos-1).changeItem(c, newItem);
        System.out.println("Set package " + setPackages.get(pos - 1).getName() + " is updated.");
    }

    public void updateSetRate(int pos, double r) {
        setPackages.get(pos - 1).setDiscountRate(r);
    }

}