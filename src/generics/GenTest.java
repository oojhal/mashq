/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ssiddiqu
 */
public class GenTest
{

    private static void getVar()
    {
        List<?> genList = new ArrayList<String>();
        // List<?> is a superclass of both List<String> and List<Integer> as ? stands for generic class
        genList = new ArrayList<Integer>();
        genList = new ArrayList<Object>();
        
    }
    public static <F extends Number> double sumNum(List<F> vals)
    {
        double sum = 0.0;
        for(F val: vals)
            sum += val.doubleValue();
        return sum;
    }
    private static void typeTest()
    {

        TestInt myvar = new TestInt<String>()
        {
            @Override
            public void printVal(String var)
            {
                System.out.println("Just printing var=" + var);
            }

        };
        myvar.printVal("whatever");
        TestInt<String> newvar = new TestIntImpl<String>();
        newvar.printVal("Something");
        GenericChain<Integer> ji;
        ji = new GenericChain();
        //ji.dowhat(new Integer(500));
        TestInt<GenericChain<Integer>> compl = new TestIntImpl<GenericChain<Integer>>();
        compl.printVal(ji);
        NestedGenType<TestIntImplGen> newVar = new NestedGenType();
        newVar.useTypeParam(new TestIntImplGen());
        
    }

    public static void main(String args[])
    {
        typeTest();
        List<Double> vals = Arrays.asList(new Double(1.0),new Double(3.0));
        System.out.println("double sum="+sumNum(vals));
    }
}
