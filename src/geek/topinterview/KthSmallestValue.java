package geek.topinterview;

import datastruct.ListHeap;

import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
/**
 * Created by ssiddiqu on 4/4/18.
 */
public class KthSmallestValue{
    public  static <K extends Comparable> K findKthSmallestValue(List<K> vals, int k) {
        Comparator<K> cmp = Comparator.<K>naturalOrder();
        ListHeap<K> lHeap = new ListHeap<K>(k, cmp);
        // kth value will be at index k-1
        for(int i=0; i< k; i++) {
            lHeap.addVal(vals.get(i));
        }
        for(int j=k; j< vals.size(); j++) {
            if(cmp.compare(vals.get(j), lHeap.getRootValue())<0) {
                lHeap.setValueAt(vals.get(j), lHeap.firstNodeIndex());
            }
        }
        return lHeap.getRootValue();
    }
    public static void testKthSmallestValue(){
        List<Integer> vals = Arrays.asList(1,2,3,4,15,11,12,8,9);
        for(int i=1; i< 7; i++) {
            System.out.println(i+"th smallest value is "+findKthSmallestValue(vals, i));
        }
        System.out.println("********************");
        List<String> valStrs = Arrays.asList("a", "b","c","e","t","p","u");
        for(int i=1; i< 7; i++) {
            System.out.println(i+"th smallest value is "+findKthSmallestValue(valStrs, i));
        }
    }
    public static void main(String[] args) {
        testKthSmallestValue();
    }
}
