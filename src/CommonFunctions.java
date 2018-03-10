
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssiddiqu
 */
public class CommonFunctions {
    public static void main(String[] args)
    {
        System.out.println("adddddd".toCharArray());
        System.out.println("0123456".substring(1));
        System.out.println("0123456".substring(1,6));
        System.out.println(Integer.valueOf("55"));
        System.out.println(String.valueOf(5));
        String[] ids = {"a","b","c","d"};
        MyList[] mlst = MyList.createList(new String[]{"a","b","c","d"});
        Arrays.sort(mlst,new Comparator<MyList>()
        {
          @Override  
          public int compare(MyList l1, MyList l2)
          {
              return l2.getID().compareTo(l1.getID());
          }
        });
        System.out.println(MyList.toString(mlst));
                Comparator<String> cmp;
        /**
         * Comparator has one method: int compare(T o1, T o2); (s1, s2) ->
         * s1.compareTo(s2) is an implementation of Comparator interface
         */
        cmp =(s1, s2) -> s1.compareTo(s2);
        Arrays.sort(mlst,(l1,l2)-> { return l2.getID().compareTo(l1.getID());});
    }
    public static class MyList{
        String m_id;
        public static String toString(MyList[] list)
        {
            String ret="";
            if((list==null)||(list.length<=0))
                return null;
            for(MyList ls:list)
            {
                ret=ret+","+ls;
            }
            return ret;
        }
        public MyList(String id)
        {
            m_id = id;
        }
        public String getID()
        {
            return m_id;
        }
        public static MyList[] createList(String[] ids)
        {
            MyList[] lst = new MyList[ids.length];
            for(int i=0;i<ids.length;i++)
            {
                lst[i]= new MyList(ids[i]);
            }
            return lst;
        }
        public String toString()
        {
            return "id = "+m_id;
        }
    }
}
