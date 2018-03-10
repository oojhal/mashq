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
public class GenericChain<M>
{
    M attr;
    public String toString()
    {
        return "ChainSub<M>:"+attr+" some more fun"; 
    }
    public M getAttr()
    {
        return attr;
    }
    public void dowhat(M var)
    {
        attr=var;
    }
}
