/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author ssiddiqu
 */
public class TestIntImplGen implements TestInt<TestIntImplGen>
{
    
    public void printVal(TestIntImplGen var)
    {
       
        System.out.println("TestIntImplGen:printVal var = ");
        //var.printVal(var);
    }  
}
