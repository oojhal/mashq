package algo;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ssiddiqu on 2/13/18.
 */
public class SortedMerge<K> {
    public List<K> merge(K[] arr1, K[] arr2, Comparator<K> cmpr) {
        if (arr1 == arr2) {
            return Arrays.asList(arr1);
        }
        if (arr1 == null) {
            return Arrays.asList(arr2);
        }
        if (arr2 == null) {
            return Arrays.asList(arr1);
        }
        List<K> result = new ArrayList<K>();
        // index to keep track of first array index
        int ind1 = 0;
        // index to keep track of second array index
        int ind2 = 0;
        while ((ind1 < arr1.length) && (ind2 < arr2.length)) {
            // while the elements in the first list are smaller
            while ((ind1 < arr1.length)&& (cmpr.compare(arr1[ind1], arr2[ind2]) <= 0) ) {
                result.add(arr1[ind1++]);
            }

            // either run out of arr1 or got to an element in arr1 where arr1[ind1] > arr2[ind2]
            // ind1 has not been added
            while ((ind2 < arr2.length) && (cmpr.compare(arr2[ind2], arr1[ind1]) < 0)) {
                result.add(arr2[ind2++]);
            }
            //ind2 has not been added
        }
        // one of the two arrays reaached the end
        while (ind1 < arr1.length) {
            result.add(arr1[ind1++]);
        }
        while (ind2 < arr2.length) {
            result.add(arr2[ind2++]);
        }


        return result;

    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 3, 5, 7, 9};
        Integer[] arr2 = {2, 4, 8};
        SortedMerge<Integer> sm = new SortedMerge<Integer>();
        System.out.println(sm.merge(arr1, arr2, (Integer i1, Integer i2) -> {
            if (i1 == i2) {
                return 0;
            }
            return i1 - i2;
        }));
        String[] arr3={"a","e","z"};
        String[] arr4={"c","m","t"};
        SortedMerge<String> sm2 = new SortedMerge<String>();
        System.out.println(sm2.merge(arr3, arr4, (String s1, String s2) -> {
            return s1.compareTo(s2);
        }));

    }
}
