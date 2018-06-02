package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ssiddiqu on 5/29/18.
 */
public class RemoveDuplicates {
    public static int[] removeDuplicates(int[] num) {
        if((num==null)||(num.length<=1))
            return num;
        // num has at least two elements
        int f=0;
        int l=1;
        List<Integer> ret = new ArrayList();
        ret.add(num[f]);
        while(l<num.length) {
            while((l<num.length)&&(num[f]==num[l])) {
                l++;
            }
            if(l<num.length) {
                // process the next different element
                ret.add(num[l]);
                f=l;
                l++;
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void testRemoveDuplicates() {
        int[] test = {1,2,3,3,3,4,4,5,6,7,7,7};
        List<Integer> lst = Arrays.stream(test).boxed().collect(Collectors.toList());
        Integer[] intArr = new Integer[lst.size()];
        intArr= lst.toArray(intArr);
        System.out.println(Arrays.toString(removeDuplicates(test)));
    }
    public static void main(String[] args) {
        testRemoveDuplicates();
    }
}
