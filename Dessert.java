/**
 * Created by Ash-0224 on 4/11/16.
 */
public class Dessert extends MenuItem{
    public Dessert(){
        super();
    }

    public Dessert(String n, double p, String d){
        super(n,p,d);
        c = Category.Dessert;
    }
}
