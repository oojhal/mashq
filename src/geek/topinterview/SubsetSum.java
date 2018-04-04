package geek.topinterview;

import java.util.Arrays;

/**
 * Given a set of non-negative integers, and a value sum,
 * determine if there is a subset of the given set with sum equal to given sum.
 * Created by ssiddiqu on 3/21/18.
 */
public class SubsetSum {
    public static boolean isSubsetSumRepeated(int[] nums, int sum) {
        int[] esum = Arrays.stream(nums).filter((n)-> n<=sum ).toArray();
        Arrays.sort(esum);
        boolean[] sumsP = new boolean[sum+1];
        // sums[i] is true if any combination of numbers in esum can be summed up-to i
        // sum equal to each number in esum array is possible
        for(int i=0;i<esum.length; i++) {
            // for all numbers
            sumsP[esum[i]]= true;
        }
        for(int i=esum[0]; i<=sum; i++) {
            if(!sumsP[i]) {
                // iterate over all the numbers
                for(int numI=0; numI< esum.length; numI++)
                {
                    int numtoUse = esum[numI];
                    if((numtoUse<=i)&&(numtoUse-i!=numtoUse)) {
                        if(sumsP[i-numtoUse]) {
                            sumsP[i]=true;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(sumsP));
        return sumsP[sum];
    }
    public static boolean isSubsetSum(int[] numsorg, int sum) {
        int[] nums = Arrays.stream(numsorg).filter((n)-> n<=sum ).toArray();
        //int[] nums = numsorg;
        boolean[][] subSetForNums = new boolean[sum+1][nums.length+1];
        for(int numsI=0; numsI<=nums.length; numsI++) {
            subSetForNums[0][numsI]=true;
        }
        // for nums =0, false
        // for sum=false
        for(int currSum=1; currSum<=sum; currSum++) {
            for(int numCnt=1; numCnt<= nums.length; numCnt++) {
                // need to make currSum with numbers upto numCnt
                // if currSum can be made with n numbers then it
                // can be made with num +1 numbers as well
                // subSetForNums have been evaluated for currSum with nums[1]..nums[numCnt-2]
                // now we have nums[numCnt-1] available
                // we can check the subSetForNums at index currSum-num[numCnt-1]
                int remainder= currSum-nums[numCnt-1];
                if(remainder>=0) {
                    subSetForNums[currSum][numCnt] = subSetForNums[currSum][numCnt-1] || subSetForNums[remainder][numCnt-1];
                }
            }
        }
        Arrays.stream(subSetForNums).forEach((r)-> System.out.println(Arrays.toString(r)));
        return subSetForNums[sum][nums.length];
    }
    public static void testIsSubsetSumRepeated() {
        int set[] = {3, 4};
        int sum = 11;
        int n = set.length;
        if (isSubsetSumRepeated(set,sum) == true)
            System.out.println("Found a subset with given sum");
        else
            System.out.println("No subset with given sum");
    }
    public static void testIsSubsetSum() {
        int set[] = {3, 4};
        int sum = 8;
        if (isSubsetSum(set,sum) == true)
            System.out.println("Found a subset with given sum");
        else
            System.out.println("No subset with given sum");
        int set2[] = new int[] {3, 4,5,17,18,20};
        sum = 12;
        if (isSubsetSum(set2,sum) == true)
            System.out.println("Found a subset with given sum");
        else
            System.out.println("No subset with given sum");
    }
    public static void main(String[] args) {
        testIsSubsetSum();
    }
}
