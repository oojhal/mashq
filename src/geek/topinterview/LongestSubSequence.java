package geek.topinterview;
import java.util.Arrays;
/**
 * Created by ssiddiqu on 3/21/18.
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of
 * a given sequence such that all elements of the subsequence are sorted in increasing order. For example,
 * the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 */
public class LongestSubSequence {
    public static int longestSubSeqSize(int[] seq) {
        int[] longLens = new int [seq.length];
        //longLens[0] is the max sequence ending at seq[0]
        //longLens[1] is the max sequence ending at seq[1]
        //longLens[i] is the max sequence ending at seq[i]
        longLens[0] =1;
        int maxSubSeq=1;
        // start from second number
        for(int i=1; i< seq.length; i++){
            // need to calculate longLens[i]
            int maxCnt = 0;
            int currNum= seq[i];
            for(int backI=i-1; backI>=0; backI--) {
                if((seq[backI]<currNum)&&(longLens[backI]>maxCnt)) {
                    maxCnt = longLens[backI];
                }
            }
            longLens[i] = maxCnt+1;
            if(longLens[i]> maxSubSeq) {
                maxSubSeq=longLens[i];
            }
        }
        System.out.println(Arrays.toString(longLens));
        return maxSubSeq;
    }
    public static void testLongestSubSeqSize() {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        System.out.println(longestSubSeqSize(arr));
        int arr2[] = new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 5 };
        System.out.println(longestSubSeqSize(arr2));
        arr2 = new int[] { 10, 9,8,7,6,5,4 };
        System.out.println(longestSubSeqSize(arr2));
    }
    public static void main(String[] args) {
        testLongestSubSeqSize();
    }
}
