package general;

/**
 * Created by ssiddiqu on 2/12/18.
 */
public class FunctionalInterExample {
    // Method that takes a "method" as argument
    static void exampleMethod(Runnable toRun) {
        toRun.run();
    }
   String interfaceConsumer(ParamRetMethod prt) {
        return prt.invokePRRMethod(this);
    }
    void extParamConsumer(ExtParamMethod exp, int[] param, int indx){
        exp.invokePRRMethod(param, indx);
    }
    void instanceMethod(Visitable vis) {
        vis.visit(this);
    }
    // Method to pass
    static void sayHello() {
        System.out.println("Hello");
    }
    public static void main(String[] args) {
        exampleMethod(new Runnable()
        // anonymous class that implements Runnable by implementing run() method
        {
            @Override
            public void run()
            {
                sayHello();
            }
        });
        exampleMethod(FunctionalInterExample::sayHello);  // prints "Hello"
        exampleMethod((()->System.out.println("Hello")));
        exampleMethod((()->{System.out.println("Hello");
        System.out.println("something else");}));
        FunctionalInterExample fie = new FunctionalInterExample();
        fie.instanceMethod((FunctionalInterExample fi)->{System.out.println(fi);});
        // no return statement needed
        System.out.println(fie.interfaceConsumer((FunctionalInterExample fi)->{return ("whaveter and "+fi);}));
        System.out.println(fie.interfaceConsumer((FunctionalInterExample fi)->"whaveter and "+fi));
        int[] param = new int[5];
        fie.extParamConsumer((int[] arr, int indx)->{arr[indx]=1;},param,0);
        fie.extParamConsumer((int[] arr, int indx)->{arr[indx]=1;},param,1);
        fie.extParamConsumer((int[] arr, int indx)->{arr[indx]=1;},param,2);
        System.out.print(param);

    }
    @FunctionalInterface
    public interface Visitable {
        public abstract void visit(FunctionalInterExample fi);
    }
    @FunctionalInterface
    public interface ParamRetMethod {
        public abstract String invokePRRMethod(FunctionalInterExample fi);
    }
    @FunctionalInterface
    public interface ExtParamMethod {
        public abstract void invokePRRMethod(int[] arr, int indx);
    }
}
