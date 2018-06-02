package useful.intiorder;

/**
 * Created by ssiddiqu on 5/30/18.
 */
public class Child extends Parent {
    protected String var="ChildVal";
    public Child() {

        System.out.println("In child default constructor");
    }
    public Child(String par) {
        super(par);
        var= par;
        System.out.println("In child param constructor");
    }
}
