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
public class TestIntImpl<T> implements TestInt<T>
{
    T myvar;
    public void printVal(T var)
    {
        myvar = var;
        System.out.println("my own implementation prininting "+myvar.toString());
    }
}
