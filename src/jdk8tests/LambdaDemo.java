/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk8tests;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author ssiddiqu
 */
@FunctionalInterface
interface Converter
{

    double convert(double input);

}
@FunctionalInterface
interface Converter2
{

    String transform(String input1, String input2);

}
@FunctionalInterface
interface ConstructDemo
{

    String transform(String input);

}
@FunctionalInterface
interface MyInterface {

    boolean authorize(int val);

    default boolean authorize(String value) {
        return true;
    }
}
public class LambdaDemo
{

    public static class MethodRef
    {
        public String justTake(String input)
        {
            return "Inside justTake " + this + " ::   "+input;
        }
    }
    public static class ConverterFact
    {

        public static double fConvert(double input)
        {
            System.out.println("Inside ConverterFact");
            return input;
        }

        public double fConverti(double input)
        {
            System.out.println("Inside Instance ConverterFact");
            return input;
        }
    }
    public static void defaultInterIvoke(MyInterface mi) {
        System.out.println(mi.authorize(9));
    }
    public static void defaulInterTest() {
        MyInterface mi = new MyInterface() {
            public boolean authorize(int val) {
                return false;
            }
        };
        defaultInterIvoke(mi);
        defaultInterIvoke((v) -> true);
    }
    public static void main(String[] args) throws Exception
    {
        final String lv = "Final String";
        defaulInterTest();
        // Convert Fahrenheit to Celsius
        System.out.println(convert(input -> (input - 32) * 5.0 / 9.0, 98.6));

        // Convert Kilometers to Miles
        System.out.println(convert(input -> input / 1.609344, 8));
        System.out.println(convert(input -> input / 1.609344, 8));
        Converter cv = (double input) ->
        {
            System.out.println(lv + " In convert method");
            return 2;
        };

        System.out.println("incoved convert. return value = " + cv.convert(0));
        System.out.println(getConverter().convert(5));
        /* 
        Method reference calling static method fConvert in class ConverterFact. 
        ConverterFact::fConvert i.e. an instance of converter where the implementation of the only method in functional
        interface Converter is: ConverterFact::fConvert
        Converter interface has following method:
        double convert(double input);
        fConvert method has following signature:
        public static double fConvert(double input)
        */
        Converter conv = ConverterFact::fConvert;
        System.out.println(conv.convert(5));  // this will call ConverterFact.fConvert(5)
        ConverterFact cf = new ConverterFact();
        /* 
        Method reference calling instance method fConverti on specific instance cf of class ConverterFact. 
        cf::fConverti i.e. .
        Converter interface has following method:
        double convert(double input);
        fConverti method has following signature:
        public double fConverti(double input)
        */
        conv = cf::fConverti; // an instance of converter where the implementation of the only method in functional
                              // interface Converter invokes fConverti method of instance cf
        System.out.println(conv.convert(5)); // this will call cf.fConverti(5)

/*
        Method reference calling an instance method on a general instance. 
        Converter2 has following method:
        String transform(String input1, String input2);
        Converter2 cv2 = String::concat => an instance of Covnerter2 that implements the String transform(String input1, String input2)
        method as an instance method concat in String class. public String concat(String str) so:
        String transform(String input1, String input2) maps to input1.concat(input2)
*/
        Converter2 cv2 = String::concat;
        System.out.println(cv2.transform("first   ","second"));
        /*
          ConstructDemo has "String transform(String input);"
          maps to the contructor in String class: String(String str)
          cv3.transform(param)  => new String(param)
        */
        ConstructDemo cv3 = String::new;
        String param="Whatever";
        System.out.println("Ivoked String constructor with "+param+": Result: "+cv3.transform(param));
        String[] stringArray =
        {
            "Barbara", "James", "Mary", "John",
            "Patricia", "Robert", "Michael", "Linda"
        };
        /*
          Arrays.sort:
          public static <T> void sort(T[] a, Comparator<? super T> c)
          stringArray => T[] a  so T is a String
          Comparator<? super String> c needs to implement following method:
          int compare(T o1, T o2) => int compare(String o1, String o2)
          In String class:
          public int compareToIgnoreCase(String str)
          int compareToIgnoreCase(String str) = maps to =>  int compare(String o1, String o2)
        */
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        // Target type: lambda body (a nested lambda)
        /*
        The only method in Callable interface is:
        V call() throws Exception;
        The only method Runnable interface is:
        public abstract void run();
        For Callable<Runnable> it becomes:
        Runnable call()
        So the implementation of call method must return an instance of Runnable
        () -> <Expression> is same as an implemenation that returns Expression
         Callable<Runnable> callable = () -> <Expression> is an implementation of call() method in Callable
         interface that returns Runnable
         Since, <Expression> must return Runnable, it must provide an implementation of run() method.
         <Expression> = () -> System.out.println("callable says hello");
         So
         System.out.println("callable says hello"); is implementation of Run() method
      
         
         */
        Callable<Runnable> callable = () -> ()
                -> System.out.println("callable says hello");
        callable.call().run();
        boolean ascendingSort = false;
        Comparator<String> cmp;
        /**
         * Comparator has one method: int compare(T o1, T o2); (s1, s2) ->
         * s1.compareTo(s2) is an implementation of Comparator interface
         */
        cmp = (ascendingSort) ? (s1, s2) -> s1.compareTo(s2)
                : (s1, s2) -> s2.compareTo(s1);

        // Target type: ternary conditional expression
        List<String> planets = Arrays.asList("Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus",
                "Neptune");
        Collections.sort(planets, cmp);
        for (String planet : planets)
        {
            System.out.println(planet);
        }

        // Target type: cast expression
        /*
      AccessController
       public static native <T> T doPrivileged(PrivilegedAction<T> action);
       The parameter is PrivilegedAction<T> which has following method:
       T run();
       The implementation of the above is:
        () -> System.getProperty("user.name")
       The above returns a String so T run() become String run().
         */
        String user = AccessController.doPrivileged((PrivilegedAction<String>) ()
                -> System.getProperty("user.name"));
        System.out.println(user);

        //  System.out.println(input -> {})
    }

    static Converter getConverter()
    {
        return (double vals) ->
        {
            System.out.println("get Converter convert method");
            return vals;
        };
    }

    static double convert(Converter converter, double input)
    {
        System.out.println("converter =" + converter);
        return converter.convert(input);
    }
}
