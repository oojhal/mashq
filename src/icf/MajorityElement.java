package icf;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssiddiqu on 2/18/18.
 */
public class MajorityElement {
    public static void findMajorityElement(int[] input) {
        Map<Integer,Integer> counter = new HashMap<>();
        for(int num:input) {
            Integer cntr = counter.get(num);
            if(cntr == null) {
                cntr = new Integer(0);
            }
            cntr = new Integer(cntr.intValue()+1);
            if(cntr>input.length/2) {
                System.out.println("Majority element found: "+num);
                return;
            }
            counter.put(num, cntr);
        }
        System.out.println("No majority element found");
    }
    public static void main(String[] args) {
        int[] elems = new int[] {1,2,3,3,4,3,1,1,1,1,1,1};
        findMajorityElement(elems);
        elems = new int[] {1};
        findMajorityElement(elems);
        elems = new int[] {1,0,3,4};
        findMajorityElement(elems);
    }
}
