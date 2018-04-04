package jdk8tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by ssiddiqu on 3/20/18.
 */
public class StreamsFilter {
    public static class Customer{
        String name;
        int age;
        public Customer(String name, int age) {
            this.name= name;
            this.age=age;
        }
        public String toString(){
            return "Customer name = "+name+" age="+age;
        }
    }
    public static class Param {
        String attrName;
        String val;
    }
    public static class EqualsPred implements Predicate<Customer> {
        Param prm;
        public EqualsPred(Param p) {
            this.prm = p;
        }
        public boolean test(Customer c) {
            return c.name.equalsIgnoreCase(prm.attrName);
        }
    }
    public static List<Customer> createCustomers(int numCust, String bareName) {
        List<Customer> lst = new LinkedList<>();
        for(int i=0; i< numCust; i++) {
            lst.add(new Customer(bareName+i, i));
        }
        return lst;
    }
    public static Predicate<Customer> getPredic(Param p) {
        return c -> c.name.equalsIgnoreCase(p.attrName);
    }
    public static void filterTest() {
        List<Customer> cst = createCustomers(10,"CustName");
        Param p = new Param();
        p.attrName="CustName0";
        Predicate<Customer> custP = getPredic(p);
        List<Customer> filC = cst.stream().filter(custP).collect(Collectors.toList());
        System.out.println(filC);

    }
    public static void main(String[] args) {
        filterTest();
    }
}
