/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 *
 * @author ssiddiqu
 */
public class CharCount
{
    int cnt;
    Map<String,CharCount> children = new HashMap<String,CharCount>();
    static List<Map<String,Integer>> charMap = new ArrayList<Map<String,Integer>>();
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));

        Scanner in = Util.getInputFromFile("charcount.txt");
        int n = in.nextInt();
        CharCount strMap= new CharCount();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            if(op.equalsIgnoreCase("add"))
            {
             strMap.addString(in.next());
            }
            else if (op.equalsIgnoreCase("find"))
            {
             String str= in.next();
             int cnt = strMap.getMatchCount(str);
             System.out.println(cnt);
             if(cnt>0)
                 System.out.println(str);
            }
        }
        
        
    }

    public void addString(String str)
        {
            if((str==null)||(str.length()==0))
                return;
            String first = str.substring(0,1);
            CharCount child = children.get(first);
            if(child==null)
            {
                child = new CharCount();
                children.put(first, child);
            }
            child.cnt++;
            child.addString(str.substring(1));
            
        }

    public int getMatchCount(String pattern)
    {
        if((pattern==null)||(pattern.length()==0))
            return cnt;
        String first = pattern.substring(0,1);
        CharCount child = children.get(first);
        if(child==null)
            return 0;
        return child.getMatchCount(pattern.substring(1));

    }
    public static int charIndx(char c)
    {
        int temp = (int)c;
        return (temp-96-1);
    }
    
}
