/**
 * Created by Ash-0224 on 4/11/16.
 */
public class Drink extends MenuItem {
    public Drink(){
        super();
    }

    public Drink(String n, double p, String d){
        super(n,p,d);
        c = Category.Drink;
    }
}
