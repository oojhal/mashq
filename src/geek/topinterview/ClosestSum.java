package geek.topinterview;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 4/4/18.
 * https://www.geeksforgeeks.org/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/
 * Given a sorted array and a number x, find a pair in array whose sum is closest to x.

 Examples:

 Input: arr[] = {10, 22, 28, 29, 30, 40}, x = 54
 Output: 22 and 30

 Input: arr[] = {1, 3, 4, 7, 10}, x = 15
 Output: 4 and 10
 */
public class ClosestSum {
    public static int[] findClosetSum(int[] vals, int targ) {
        int diff = Integer.MAX_VALUE;
        int bIndx=0;
        int eIndx=vals.length-1;
        int[] valIndx = new int[]{vals[bIndx],vals[eIndx]};
        while(bIndx<eIndx) {
            int cdiff= targ-(vals[bIndx]+vals[eIndx]);
            int acdiff = Math.abs(cdiff);
            if(acdiff<diff) {
                valIndx[0]=vals[bIndx];
                valIndx[1]=vals[eIndx];
                diff = acdiff;
            }
            // targ > current sum
            if(cdiff==0) {
                // can't get smaller than this
                break;
            }
            if(cdiff>0) {
                bIndx++;
            }
            else {
                eIndx--;
            }
        }
        return valIndx;
    }
    public static void testFindClosestSum() {
        int[] vals = {10, 22, 28, 29, 30, 40};
        int sum = 54;
        System.out.println(Arrays.toString(findClosetSum(vals,sum)));
    }
    public static void main(String[] args) {
        testFindClosestSum();
    }
}
