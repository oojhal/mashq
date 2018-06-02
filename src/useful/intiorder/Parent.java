package useful.intiorder;

/**
 * Created by ssiddiqu on 5/30/18.
 */
public class Parent {
    protected String var="ParentVal";
    public Parent() {
        System.out.println("In parent default constructor");
    }
    public Parent(String par) {

        var= par;
        System.out.println("In parent param constructor");
    }
}
