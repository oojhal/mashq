/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

/**
 *
 * @author ssiddiqu
 */
public class MultipleParamsTest {
    public static String concatenate(String... strings)
    {
      if((strings==null)||(strings.length==0))
          return null;
      
      String ret=strings[0];
      for(int i=1; i<strings.length; i++)
          ret= ret+","+strings[i];
      return ret;
    }
    public static void main(String[] vars)
    {
        System.out.println(concatenate((String) null));
        System.out.println(concatenate("one"));
        System.out.println(concatenate("one","two"));
       
    }
}
