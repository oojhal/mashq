package interviews.interviewbit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 6/3/18.
 */
public class ArrayNum {
    public static ArrayList<Integer> plusOneO(ArrayList<Integer> A) {
        if((A==null)||(A.size()==0))
            return null;
        StringBuilder strb = new StringBuilder();
        for(Integer n: A) {
            strb.append(n);
        }
        String numStrb = strb.toString();
        String numStr = numStrb.replaceFirst("^0+(?!$)", "");
        Integer iVal = Integer.valueOf(numStr);
        Integer incI = new Integer(iVal.intValue()+1);
        String temp = Integer.toString(incI);
        ArrayList<Integer> ret  = new ArrayList<>();
        for (int i = 0; i < temp.length(); i++)
        {
            ret.add(temp.charAt(i) - '0');
        }
        return ret;
    }
    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        if((A==null)||(A.size()==0))
            return null;

        Iterator<Integer> it = A.iterator();
        while(it.next()==0) {
            it.remove();
        }
        int lInd = A.size()-1;
        while((lInd>=0)&&(A.get(lInd) ==9)) {
            A.set(lInd,0);
            lInd--;
        }
        // lInd is where need to add. This can be first
        if(lInd<0) {
            A.add(0,1);
        }
        else {
            A.set(lInd, new Integer(A.get(lInd))+1);
        }
        return A;
    }

    public static void arrayListTest() {
        List<Integer>  ip = Arrays.asList(0,9,9);
        ArrayList<Integer> numL = new ArrayList<Integer>(ip);
        System.out.println(plusOne(numL));
    }
    public static void main(String[] args) {
        arrayListTest();
    }
}
