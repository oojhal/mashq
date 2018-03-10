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
public class NestedGenType<T extends TestInt<T>>
{
    public void useTypeParam(T p)
    {
        p.printVal(p);
    }
}
