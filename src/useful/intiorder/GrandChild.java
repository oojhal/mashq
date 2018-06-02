package useful.intiorder;

/**
 * Created by ssiddiqu on 5/30/18.
 */
public class GrandChild extends Child {
    protected String var=getVal();
    private String getVal() {
        System.out.println("In Grandchild param initializer");
        return "GrandChildVal";
    }
    public GrandChild() {
        System.out.println("In GrandChild default constructor");
    }
    public GrandChild(String par) {
        super(par);
        var= par;
        System.out.println("In GrandChild param constructor");
    }
    public GrandChild(String one, String two) {
        System.out.println("In GrandChild constructor with two params:");
    }
    public static void main(String[] args) {
        /*
        In parent default constructor
        In child default constructor
        In Grandchild param initializer
        In GrandChild default constructor
        ********** One param constructor ***************
        In parent param constructor
        In child param constructor
        In Grandchild param initializer
        In GrandChild param constructor
        ********** Two params constructor ***************
        In parent default constructor
        In child default constructor
        In Grandchild param initializer
        In GrandChild constructor with two params:
        */
        GrandChild gc = new GrandChild();
        System.out.println("********** One param constructor ***************");
        gc  = new GrandChild("grand");
        System.out.println("********** Two params constructor ***************");
        gc  = new GrandChild("grand","what");
    }
}
