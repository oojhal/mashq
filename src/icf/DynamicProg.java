package icf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssiddiqu on 3/3/18.
 */
public class DynamicProg {
    public static int fibn(int order) {
        if (order <= 0)
            return 0;
        int[] dyn = new int[order + 1];
        dyn[0] = 1;  // first fib
        dyn[1] = 1;  // second fib
        for (int i = 2; i < dyn.length; i++) {
            dyn[i] = dyn[i - 1] + dyn[i - 2];
        }
        return dyn[order - 1];
    }

    public static void testfibn() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " th fib is " + fibn(i));
        }
    }

    /**
     * Let us say that you are given a number N,
     * you've to find the number of different ways to write it as the sum of 1, 3 and 4.
     */
    public static int sumWays(int sum, int[] nums) {
        if (sum == 0)
            return 0;
        //find sum for all the numbers from 0- sum
        int[] sumCount = new int[sum + 1];
        for (int i = 0; i < sumCount.length; i++) {
            sumCount[i] = -1;
        }
        // assuming nums in sum are ordered
        // number of ways to sum the smallest number is num is 1.
        sumCount[nums[0]] = 1;
        for (int currSum = nums[0] + 1; currSum <= sum; currSum++) {
            int currSumCnt = 0;
            for (int i = 0; i < nums.length; i++) {
                // number of ways to sum the number nums[i] is sumCount[nums[i]]
                // number of ways to sum the number sum - nums[i] is sumCount[sum- nums[i]]
                int diff = currSum - nums[i];
                if ((diff > 0) && (diff < sum) && (sumCount[diff]) > 0) {
                    currSumCnt += sumCount[diff];
                }
            }
            sumCount[currSum] = currSumCnt;
        }
        System.out.println("Num of ways: ");
        printArr(sumCount, "Ways to get %d is %d");
        return sumCount[sum];
    }

    public static void testSumWays() {
        int sum = 15;
        int[] nums = new int[]{1, 2, 3};
        System.out.println("No of ways to make sum " + sum + " with nums " + nums + " is:" + sumWays(sum, nums));
    }

    /**
     * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S.
     * Find the minimum number of coins the sum of which is S (we can use as many coins
     * of one type as we want), or report that it’s not possible to select coins in such
     * a way that they sum up to S.
     *
     * @param sum
     * @param coins
     * @return
     */
    public static int minCoinSum(int sum, int[] coins) {
        //minCount[i] represents the miniumum number of coins to reach sum i
        int[] minCount = new int[sum + 1];
        // assume input is valid
        for (int i = 0; i < coins.length; i++)
            minCount[coins[i]] = 1;
        if (minCount[sum] > 0) {
            return minCount[sum];
        }
        // assume minimum coin is coin[0]
        // the minimum sum is the smallest coin
        // to reach minimum sum of currSum, find minCount[currSum-coins[i]] and add coin[i]
        // for each i and get the minimum
        for (int currSum = coins[0] + 1; currSum <= sum; currSum++) {
            // if the sum count has already been initialized
            if (minCount[currSum] > 0)
                continue;
            // no way to reach currSum
            int minCurrSumCnt = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int prevIndx = currSum - coins[i];
                // check the min count to reach the sum prevInd
                // a value of 0 means there is no way to reach the sum
                if ((prevIndx > 0) && (prevIndx <= sum) && (minCount[prevIndx] > 0)) {
                    // there are minCount[prevIndx]+1 ways to reach currSum
                    if (minCurrSumCnt > minCount[prevIndx] + 1) {
                        minCurrSumCnt = minCount[prevIndx] + 1;
                    }
                }
            }
            // we found minimum number of ways to reach currSum
            if (minCurrSumCnt < Integer.MAX_VALUE) {
                minCount[currSum] = minCurrSumCnt;
            }
        }
        System.out.println("Minimum number of coins needed to reach a value ");
        printArr(minCount, "Minimum number of coins %d are needed to reach %d" + System.lineSeparator());
        return minCount[sum];
    }

    private static void printArr(int[] arr, String strTemp) {
        final int[] cntr = new int[1];
        cntr[0] = 0;
        Arrays.stream(arr).forEach((n) -> {
            System.out.printf(strTemp, n, (cntr[0]++));
        });
    }

    public static void testMinCoinSum() {
        int sum = 40;
        int[] coins = new int[]{5, 10, 25};
        printArr(coins, "coin number %d is %d" + System.lineSeparator());
        System.out.println("minimum number of ways to reach value " + sum + " is " + minCoinSum(sum, coins));
    }

    public static List<Integer> maxIncrSequence(int[] seq) {
        List<List<Integer>> maxSeqs = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(seq[0]);
        // first increasing list has the first element
        List<Integer> maxSeq = first;
        maxSeqs.add(first);
        // iterate over all elements to find the max list at each element
        for (int i = 1; i < seq.length; i++) {
            // get the max sequence at prev index
            List<Integer> prevSeq = maxSeqs.get(i - 1);
            // if the current element is greater than the last element of the last sequence then the new sequence
            // is the old sequence plus the current element
            List<Integer> currSeq = new ArrayList<>();
            if (seq[i] > prevSeq.get(prevSeq.size() - 1)) {
                currSeq.addAll(prevSeq);
            }
            currSeq.add(seq[i]);
            if (currSeq.size() > maxSeq.size())
                maxSeq = currSeq;
            maxSeqs.add(currSeq);
        }
        return maxSeq;
    }

    public static void testMaxIncrSequence() {
        int[] seq = new int[]{1, 2, 3, 5, 17, 18, 19, 29, 4, 3, 5, 6, 7, 8, 9, 10};
        System.out.println(maxIncrSequence(seq));
    }

    /**
     * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total
     * value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent
     * values and weights associated with n items respectively. Also given an integer W which represents knapsack
     * capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller
     * than or equal to W
     *
     * @param values
     * @param weights
     * @return Weight repetition allowed
     */
    public static int knapsackRep(int[] values, int[] weights, int twt) {
        //assume the two input are sorted
        int[] maxVals = new int[twt + 1];
        // maximum value for the minimum weight is corresponding value
        maxVals[weights[0]] = values[0];
        // iterate over until you get the target wt
        for (int currWt = weights[0] + 1; currWt <= twt; currWt++) {
            // initialize current max weight to be infinity
            int currMaxVal = -1;
            for (int wtInd = 0; wtInd < weights.length; wtInd++) {
                // prevWt to check is current
                if ((currWt == weights[wtInd]) && (values[wtInd] > currMaxVal)) {
                    currMaxVal = values[wtInd];
                }
                int prevWt = currWt - weights[wtInd];
                // if the prevWt value is valid
                if ((prevWt > 0) && (prevWt <= twt)) {
                    // if there is a valid value for prevWt
                    if (maxVals[prevWt] > 0) {
                        int candMaxVal = maxVals[prevWt] + values[wtInd];
                        if (candMaxVal > currMaxVal) {
                            currMaxVal = candMaxVal;
                        }
                    }
                }
            }
            if (currMaxVal != -1) {
                maxVals[currWt] = currMaxVal;
            }
        }
        printArr(maxVals, "Maximum value is %d for weight %d" + System.lineSeparator());
        return maxVals[twt];
    }

    /**
     * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total
     * value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent
     * values and weights associated with n items respectively. Also given an integer W which represents knapsack
     * capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller
     * than or equal to W
     *
     * @param values
     * @param weights
     * @return Weight repetition allowed
     */
    public static int[] knapsackNoRep(int[] values, int[] weights, int twt) {
        // at maxValSets[i] points to a row of weight indices that provide the max set to achieve weight i;
        int[][] maxValSets = new int[twt + 1][];
        for (int i = 0; i < weights.length; i++) {
            //there is only one value[i] to achieve maximum weight i
            maxValSets[weights[i]] = new int[]{i};
        }

        //       maxValSets[weights[0]] = new int[]{0};
        int currWt = weights[0];
        while (currWt <= twt) {
            // start with weights at currWt
            for (int wtIndx = 0; wtIndx < weights.length; wtIndx++) {
                // these are set of indices that provide current max value at currWt
                int[] currMaxIndices = maxValSets[currWt];
                // if the current weight index has not already been used
                int nextWt = currWt + weights[wtIndx];
                if ((nextWt <= twt) && (!contains(currMaxIndices, wtIndx))) {
                    // need to find weight indices at currWt + weight[wtIndx]
                    int nextMaxWithCurr = Arrays.stream(currMaxIndices).map((ind) -> values[ind]).sum() + values[wtIndx];
                    int[] nextCurrMaxIndices = maxValSets[nextWt];
                    int nextCurrMax = 0;
                    if (nextCurrMaxIndices != null) {
                        nextCurrMax = Arrays.stream(nextCurrMaxIndices).map((ind) -> values[ind]).sum();
                    }
                    // if
                    if (nextMaxWithCurr > nextCurrMax) {
                        int[] newMaxArr = new int[currMaxIndices.length + 1];
                        System.arraycopy(currMaxIndices, 0, newMaxArr, 0, currMaxIndices.length);
                        // at the last element add the current weight index
                        newMaxArr[currMaxIndices.length] = wtIndx;
                        maxValSets[nextWt] = newMaxArr;
                    }
                }
            }
            currWt++;
            while ((currWt <= twt) && (maxValSets[currWt] == null)) {
                currWt++;
            }
        }
        int[] tinds = maxValSets[twt];
        if (tinds != null) {
            int maxVal = 0;
            System.out.println("For target weight " + twt + " use:");
            for (int i : tinds) {
                System.out.print("weight " + weights[i] + " val " + values[i] + " ,");
                maxVal += values[i];
            }
            System.out.println(" Total value = " + maxVal);
        }
        return maxValSets[twt];

    }

    // A utility function that returns maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int webknapSackDyna(int W, int wt[], int val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];
        //K[i][w] represents the max value for weight w by using i elements
        // row i represents using i values
        // col w represents the current weight
        // Build table K[][] in bottom up manner
        // n is the number of values available to use
        // for each available value
        for (i = 0; i <= n; i++) {
            // for each weight until target weight is achieved
            for (w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    K[i][w] = 0;
                    // if the previous weight is still less than the target weight it can potentially be included
                    // to calculate the max val for current weight
                else if (wt[i - 1] <= w)
                    // val[i-1]: value for the i th weight
                    // K[i-1][w-wt[i-1]]: max value using i-1 values to achieve weight target weight - wt of i-1 element
                    // val[i-1] + K[i-1][w-wt[i-1]] -> used ith weight add val[i-] and use the remaining i-1 weights to
                    // get to the target weight w-wt of ith element -> wt-wt[i-1]
                    // K[i-1][w] => max value using i-1 elements to achieve weight w i.e. not using ith element
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                    // wt[i-1] > w
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    // Returns the maximum value that can be put in a knapsack of capacity W with n values
    static int webknapSackRecur(int W, int wt[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // wt[i] represents the weight of the ith item
        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n - 1] > W)
            return webknapSackRecur(W, wt, val, n - 1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else return max(val[n - 1] + webknapSackRecur(W - wt[n - 1], wt, val, n - 1),
            webknapSackRecur(W, wt, val, n - 1)
        );
    }

    static void testwebknapsack() {
        {
            int val[] = new int[]{60, 100, 120};
            int wt[] = new int[]{10, 20, 30};
            int W = 50;
            int n = val.length;
            System.out.println(webknapSackDyna(W, wt, val, n));
        }
    }

    static boolean contains(int[] vals, int tar) {
        if (vals == null)
            return false;
        for (int val : vals) {
            if (val == tar)
                return true;
        }
        return false;
    }

    public static void arrTest() {
        int[][] twodim = new int[10][];
        twodim[0] = new int[5];
        twodim[1] = new int[10];
    }

    public static void testKnapsack() {
        int[] vals = new int[]{60, 100, 120};
        int[] weights = new int[]{10, 20, 30};
        int weight = 50;
        System.out.println(knapsackRep(vals, weights, weight));
    }

    public static void testKnapsackNoRep() {
        int[] vals = new int[]{60, 100, 120};
        int[] weights = new int[]{10, 20, 30};
        int weight = 50;
        System.out.println(knapsackNoRep(vals, weights, weight));
    }

    public static String longestSubSequenceFinal(String str1, String str2) {
        int st1 = str1.length();
        int st2 = str2.length();
        String[][] seq = new String[st1 + 1][st2 + 1];
        for (int firstLen = 0; firstLen <= st1; firstLen++) {
            for (int secondLen = 0; secondLen <= st2; secondLen++) {
                // either string with 0 length is intiialized with nulls
                if ((firstLen == 0) || (secondLen == 0)) {
                    seq[firstLen][secondLen] = "";
                }
                // both firstLen and secondLen are > 0
                // if the new character in both strings is identical
                // seq[i][j] represents longest common sustring with i characters
                // in str1 and j characters in str2
                // ith character in str1 is str[i-1]
                else if (str1.charAt(firstLen - 1) == str2.charAt(secondLen - 1)) {
                    seq[firstLen][secondLen] = seq[firstLen - 1][secondLen - 1] + str1.charAt(firstLen - 1);
                } else {
                    seq[firstLen][secondLen] = getLongerString(seq[firstLen - 1][secondLen], seq[firstLen][secondLen - 1]);
                }

            }
        }
        return seq[st1][st2];
    }

    private static String getLongerString(String str1, String str2) {
        if ((str1 == null) || (str1.isEmpty())) {
            return str2;
        }
        if ((str2 == null) || (str2.isEmpty())) {
            return str1;
        }
        return (str1.length() > str2.length()) ? str1 : str2;
    }

    public static String longestSubSequence(String str1, String str2) {
        return longestSubSequence(str1, 0, str2, 0);
    }

    public static String longestSubSequence(String str1, int st1, String str2, int st2) {
        // no characters left in either of the two strings
        if ((st1 >= str1.length()) || (st2 >= str2.length()))
            return null;
        String subStr1 = str1.substring(st1, str1.length());
        String subStr2 = str2.substring(st2, str2.length());
        String lonSeq1 = null;
        String lonSeq2 = null;
        if (subStr1.length() == 1) {
            lonSeq1 = (str2.contains(subStr1)) ? subStr1 : null;
        } else {
            lonSeq1 = longestSubSequence(str1, st1 + 1, str2, st2);
            if ((lonSeq1 != null) && (!lonSeq1.isEmpty())) {
                String sub2 = str2.substring(0, str2.indexOf(lonSeq1.charAt(0)));
                if ((sub2 != null) && (!sub2.isEmpty())) {
                    if (sub2.contains(String.valueOf(str1.charAt(st1)))) {
                        lonSeq1 = str1.charAt(st1) + lonSeq1;
                    }
                }
            }
        }
        if (subStr2.length() == 1) {
            lonSeq2 = (str1.contains(subStr2)) ? subStr2 : null;
        } else {
            // find longestSequence with str1 index increased
            lonSeq2 = longestSubSequence(str1, st1, str2, st2 + 1);
            if ((lonSeq2 != null) && (!lonSeq2.isEmpty())) {
                // find the index of longSeq2 in str1
                String sub1 = str1.substring(0, str1.indexOf(lonSeq2.charAt(0)));
                if ((sub1 != null) && (!sub1.isEmpty())) {
                    if (sub1.contains(String.valueOf(str2.charAt(st2)))) {
                        lonSeq2 = str2.charAt(st2) + lonSeq2;
                    }
                }
            }

        }

        if ((lonSeq1 == null) || (lonSeq1.isEmpty())) return lonSeq2;
        if ((lonSeq2 == null) || (lonSeq2.isEmpty())) return lonSeq1;
        return (lonSeq1.length() > lonSeq2.length()) ? lonSeq1 : lonSeq2;
    }

    public static List<List<String>> breakIntoWords(String longWord, Collection<String> dict) {
        // array where each element contains a list of lists
        // each list represents words to get to the longWord substring of length at index
        List<List<String>>[] words = new ArrayList[longWord.length() + 1];
        // words[i] = list of matching words at longWord length i
        // words[longWord.length()] is answer
        Arrays.stream(words).forEach((wl) -> wl = null);
        // contains empty list of lists
        // the first element contains an emtpty list of strings
        words[0] = new ArrayList<List<String>>();

        int beginIndx = -1;
        while (beginIndx < longWord.length()) {
            // beginIndx starts from 0 and goes up to length of the word -1
            // start with -1 and first index where the words[beginIndx]  != null is 0
            // last value for beingIndx is wordlength -1
            while ((words[++beginIndx] == null) && (beginIndx < longWord.length())) ;
            // non null List of Lists of String
            // for index 0 it will be empty. For subsequent indices it may be non empty
            List<List<String>> beginIndxLists = words[beginIndx];
            // if beginIndx has not reached the end of the word
            // last value of beingIndx is longWord.length-1
            // need to compare the last character for which substring(wordlength-1, wordlength)
            if (beginIndx < longWord.length()) {
                // beginIdx is the start of the word, end Indx is end
                // last value for endIndx is longWord.length
                for (int endIndx = beginIndx + 1; endIndx <= longWord.length(); endIndx++) {
                    String matchW = longWord.substring(beginIndx, endIndx);
                    if ((matchW != null) && (!matchW.isEmpty())) {
                        if (dict.contains(matchW)) {
                            // list of list of Strings for getting to the endIndx
                            List<List<String>> mwordsList = words[endIndx];
                            if (mwordsList == null) {
                                mwordsList = new ArrayList<List<String>>();
                            }
                            if((beginIndxLists==null)||(beginIndxLists.isEmpty())) {
                                mwordsList.add(new ArrayList(Arrays.asList(matchW)));
                            }
                            // words[endIndx] has list of list initialized
                            // there is a list at beginIndx
                            // get the list of words at begingIndx. It contains at least one empty list
                            else {
                                for (List<String> beginList : beginIndxLists) {
                                    // at the matched word in the list at beginIndx and then copy it to mwordsList at endIndx
                                    beginList.add(matchW);
                                    mwordsList.add(beginList);
                                }
                            }
                            words[endIndx] = mwordsList;
                        }
                    }
                }

            }

        }
        return words[longWord.length()];
    }
    public static void testBreakIntoWords () {
        String str = "catsanddog";
        List<String> words = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(breakIntoWords(str,words));

    }
    public static int getMaxCoinVal(int[] coins) {
        if((coins.length&1)!=0) {
            System.out.println("Unfair number of coins");
        }
        return getMaxCoinVal(coins, 0, coins.length-1);
    }
    public static int getMaxCoinVal(int[] coins, int startIndx, int endIndx) {
        // only 1 value
        if(startIndx==endIndx) {
            return coins[startIndx];
        }
        int sum = 0;
        for(int i=startIndx; i<= endIndx; i++) {
            sum += coins[i];
        }
        return getMaxVal(sum-getMaxCoinVal(coins,startIndx+1,endIndx),sum-getMaxCoinVal(coins, startIndx, endIndx-1));

    }
    public static void testGetMaxCoinVal() {
        int[] coins = new int[]{8,15,3,7};
        System.out.println("Max coin value = "+ getMaxCoinVal(coins));
    }
    private static int getMaxVal(int val1, int val2) {
        return (val1> val2)? val1:val2;
    }

    static int getMaxCoinValMine(int vals[], int n) {
        // initialize array of size n,n
        int[][] maxVals = new int[n][n];
        for(int wind = 0; wind < n; wind++) {
            // start is always less than or equal to end
            for(int start=0, end = start+wind; end<n; start++,end++ ) {
                // no elements element
                if(wind==0) {
                    maxVals[start][end] = 0;
                }
                else if(start == end) {
                    // only single element
                    maxVals[start][end] = vals[start];
                }
                else {
                    // start >=0
                    // calculate wind[start][end]
                    // chose end, then value is val[end] for P1, val[start][end-1] for P2 and val[start][end-2] for P1
                    int valEnd = vals[end] + (( start < end-2)? maxVals[start][end-2]:0);
                    // chose start, then value is val[start] for P1, val[start+1][end] for P2 and val[start+2][end] for P1
                    int valStart = vals[start] + ((start+2 < end)? maxVals[start+2][end]:0);
                    maxVals[start][end]  = max(valEnd, valStart);
                }
            }
        }
        return maxVals[0][n-1];

    }
    // Utility functions to get maximum
    // and minimum of two integers

    int min(int a, int b) { return a < b ? a : b; }

    // Returns optimal value possible that a player
    // can collect from an array of coins of size n.
    // Note than n must be even
    static int getMaxCoinValGeeks(int arr[], int n)
    {
        // Create a table to store solutions of subproblems
        int table[][] = new int[n][n];
        int gap, gapStartIndx, gapEndIndx, x, y, z;

        // Fill table using above recursive formula.
        // Note that the tableis filled in diagonal
        // fashion (similar to http://goo.gl/PQqoS),
        // from diagonal elements to table[0][n-1]
        // which is the result.
        for (gap = 0; gap < n; ++gap)
        {
            // both gapStartIndx and gapEndIndx are incremented in each loop iteration
            // for each gap, gapStartIndx keeps moving
            // gapEndIndx is always gap more than the gapStartIndx
            // when gap == 0, gapStartIndx = gapEndIndx
            // table[i,j] identifies the max value obtained by the player who picks first
            // first player = i to get val[i] , range of coins left = i+1, j
            // second player max value = table[i+1,j], range of coins left = i+2, j
            // first player max value = table[i+2,j]. So total value for player 1 is
            // val[i] + table[i+2, j]
            // first player picks j to get val[j], range of coins left i, j-1.
            // second player max = table[i, j-1]  range of coins left i, j-2
            // first player max = table[i,j-2]
            // so for finding max value for a p
            for (gapStartIndx = 0, gapEndIndx = gap; gapEndIndx < n; ++gapStartIndx, ++gapEndIndx)
            {
                // Here x is value of F(i+2, j),
                //  y is F(i+1, j-1) and z is
                // F(i, j-2) in above recursive formula
                // if gapStartIndx and gapEndIndx are two or more apart
                x = ((gapStartIndx + 2) <= gapEndIndx) ? table[gapStartIndx + 2][gapEndIndx] : 0;
                y = ((gapStartIndx + 1) <= (gapEndIndx - 1)) ? table[gapStartIndx +1 ][gapEndIndx -  1] : 0;
                z = (gapStartIndx <= (gapEndIndx - 2)) ? table[gapStartIndx][gapEndIndx - 2]: 0;

                table[gapStartIndx][gapEndIndx] = Math.max(arr[gapStartIndx] +
                    Math.min(x, y), arr[gapEndIndx] +
                    Math.min(y, z));
            }
        }

        return table[0][n - 1];
    }
    public static int getMaxSizeSubMatrix(int[][] indices) {
        // search for lower left corners
        int maxSq = 0;
        for(int row=0; row< indices.length; row++) {
            for(int col=0; col< indices[row].length; col++) {
                // found a potential candidate
                if(indices[row][col]!=0) {
                    if((row-1>=0)&&(col-1>=0)) {
                        int prevSqrSize = indices[row-1][col-1];
                        if(prevSqrSize> 0) {
                            // go back up
                            boolean newSq = true;
                            for (int up = row; up >= row - prevSqrSize; up--) {
                                if (indices[up][col] < 1) {
                                    newSq = false;
                                    break;
                                }
                            }
                            if (newSq) {
                                // go back left
                                for (int left = col; left >= col - prevSqrSize; left--) {
                                    if (indices[row][left] < 1) {
                                        newSq = false;
                                        break;
                                    }
                                }

                            }
                            if(newSq) {
                                indices[row][col] = indices[row-1][col-1]+1;
                                if(indices[row][col]> maxSq) {
                                    maxSq = indices[row][col];
                                }
                            }
                        }

                    }
                }
            }
        }
        return maxSq;

    }
    public static void testMaxSizeSubMatrix() {
        int[][] matrix = new int[][]{
            {0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}};
        System.out.println(getMaxSizeSubMatrix(matrix));
         matrix = new int[][]{
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}};
        System.out.println(getMaxSizeSubMatrix(matrix));
    }
    public static void testGetMaxCoinValGeeks() {
        int[] coins = new int[]{8,15,3,7};
        System.out.println("Max coin value = "+ getMaxCoinValGeeks(coins, coins.length));
    }
    public static void testGetMaxCoinValMine() {
        int[] coins = new int[]{8,15,3,7};
        System.out.println("Max coin value = "+ getMaxCoinValMine(coins, coins.length));
    }
    public static void testEvenOdd() {
        for(int i=0; i< 10; i++) {
            System.out.println(i+" is "+(((i&1)==0)? "even":"odd"));
        }

    }
    public static void testLongestSubSequence() {
        String str1="ABCDGH";
        String str2 = "AEDFHR";
        System.out.println(longestSubSequence(str1,str2));
        str1= "AGGTAB";
        str2= "GXTXAYB";
        System.out.println(longestSubSequence(str1,str2));

    }

    public static void testLongestSubSequenceFinal() {
        String str1="ABCDGH";
        String str2 = "AEDFHR";
        System.out.println(longestSubSequenceFinal(str1,str2));
        str1= "AGGTAB";
        str2= "GXTXAYB";
        System.out.println(longestSubSequenceFinal(str1,str2));

    }
    public static int maxDigitsKeyPad(int digits, int startDigit) {
        Map<Integer,int[]> numMap = new HashMap<Integer,int[]>() {{
           put(1, new int[]{8,6});
           put(2, new int[]{7,9});
            put(3, new int[]{8,4});
            put(4, new int[]{3,9});
            put(5, null);
            put(6, new int[]{1,7});
            put(7, new int[]{2,6});
            put(8, new int[]{1,3});
            put(9, new int[]{2,4,0});
            put(0, new int[]{5});

        }};
        // row represents the number of digits
        // col represents the starting digit
        // digit[row][col] represents different numbers that can be formed
        // of row digits when using col as the starting digit
        // for 10 digits need 11 slot as digit[10] will the combinations when
        // using 10 digits
        int[][] digitMax = new int[digits+1][numMap.size()];
        for(int numDigits =0; numDigits<= digits; numDigits++) {
            // digitNum go from 0-10
            for(int digitNum=0; digitNum<numMap.size();digitNum++) {
                // if there are 0 digits to use no combinations possible
                if(numDigits==0) {
                    digitMax[numDigits][digitNum] = 0;
                }
                // if there is one digit that is the only one in the combination
                else if(numDigits == 1) {
                    digitMax[numDigits][digitNum] =1;
                }
                else {
                    // these digits are reachable from the current digit
                    int[] nextDigits = numMap.get(digitNum);
                    // from current digit, can reach any of the digits in nextDigits
                    // and have n-1 more digits to cover
                    // if next digit
                    int comb = 0;
                    if(nextDigits!=null) {
                        for(int nextDigit:nextDigits) {
                            comb += digitMax[numDigits-1][nextDigit];

                        }
                    }
                       digitMax[numDigits][digitNum] = comb;
                    }
                }
            }
        return digitMax[digits][startDigit];
    }
    public static int numWaysToClimb(int[] allowedSteps, int numStairs) {
        // numWays[i] = number of ways to reach i steps
        // numWays[numStairs] = number of ways to reach numStairs steps
        // allowedSteps array contains the legal allowed steps
        int[] numWays = new int[numStairs+1];
        // this is to capture the case where the all the steps can be climbed at once
        // by taking allowedSteps. See below
        numWays[0] = 1;
        // if there are 0 stairs, there are 0 ways to climb. numWays[0] is already 0
        // if there are 1 stairs, the only way to climb it is if 1 step is in the list
        // of allowedSteps. This was already covered in the previous loop
        // 0 and 1 stairs already covered so we can start from 2 stairs
        for(int currentStairs = 1; currentStairs <= numStairs; currentStairs++) {
            // numWaysCurrent keeps track of number of ways to climb currentStairs
            // numWaysCurrent intialized to 0
            int numWaysCurrent = 0;
            for(int allowedStep: allowedSteps) {
                int prevWaysIndx = currentStairs-allowedStep;
                // if the currentStairs == allowedStep then currentStairs can be climbed in one turn
                // by taking allowedStep
                // For this case prevWaysIndx = 0
                // that is why numWays[0] has been initialized to 1
                // this indicates that there is one way to climb all of the currentStairs
                if(prevWaysIndx>=0) {
                    numWaysCurrent += numWays[prevWaysIndx];
                }
            }
            numWays[currentStairs] = numWaysCurrent;
        }
        return numWays[numStairs];
    }

    public static List<List<Integer>> minCoinsForMakingChange(int[] coinVals, int amount) {
        // minWays[i] = lists of min coins that can make value i
        // numWays[amount] = lists of min coins that can make value amount
        // coinVals array contains values of allowed coins
        List<List<Integer>>[] minWays = new ArrayList[amount + 1];
        // no coins to make value 0
        minWays[0] = new ArrayList<>();
        for (int currAmount = 1; currAmount <= amount; currAmount++) {
            List<List<Integer>> minCoinLists = null;
            for (int coinVal : coinVals) {
                int prevVal = currAmount - coinVal;
                // reached the currAmount using a singleCoin of coinVal
                // that's the shortest way. No need to check any further
                if (prevVal == 0) {
                    // list with single coin
                    minCoinLists = new ArrayList(Arrays.asList(Arrays.asList(coinVal)));
                    break;
                } else if (prevVal > 0) {
                    List<List<Integer>> prevValCoinLists = minWays[prevVal];
                    // get the min list of coins to reach prevVal. if such a list exits
                    // i.e. there is a way to use the coins to reach prevVal
                    if ((prevValCoinLists != null) && (!prevValCoinLists.isEmpty())) {
                        // compare the number of coins with the prevMin number of coins
                        if (((minCoinLists == null) || (minCoinLists.isEmpty())) || (minCoinLists.get(0).size() > prevValCoinLists.get(0).size() + 1)) {
                            minCoinLists = new ArrayList();
                        }
                        addLists(prevValCoinLists, minCoinLists, coinVal);
                    }
                }

            }
            minWays[currAmount] = minCoinLists;
        }
        return minWays[amount];
    }
    private static void addLists(List<List<Integer>> sourceLists, List<List<Integer>> destLists, int coinVal) {
        for(List<Integer> sourceList: sourceLists) {
            List<Integer> lst = new ArrayList(sourceList);
            lst.add(coinVal);
            Collections.sort(lst);
            if(!destLists.contains(lst)) {
                destLists.add(lst);
            }
        }
    }
    public static void testMinCoinsForMakingChange() {
        int[] coins = new int[] {1,2,3};
        int amount= 5;
        System.out.println("Min coin lists to make amount "+amount+" using allowed coins: "+Arrays.toString(coins)+ " is "+minCoinsForMakingChange(coins, amount));
        amount = 4;
        System.out.println("Min coin lists to make amount "+amount+" using allowed coins: "+Arrays.toString(coins)+ " is "+minCoinsForMakingChange(coins, amount));
        coins = new int[] {1,2,3};
        amount= 5;
        System.out.println("Min coin lists to make amount "+amount+" using allowed coins: "+Arrays.toString(coins)+ " is "+minCoinsForMakingChange(coins, amount));
        coins = new int[] {1,2,3,4};
        amount= 9;
        System.out.println("Min coin lists to make amount "+amount+" using allowed coins: "+Arrays.toString(coins)+ " is "+minCoinsForMakingChange(coins, amount));
        coins = new int[] {3};
        amount= 4;
        System.out.println("Min coin lists to make amount "+amount+" using allowed coins: "+Arrays.toString(coins)+ " is "+(minCoinsForMakingChange(coins, amount)==null? "none":minCoinsForMakingChange(coins, amount)));
    }
    public static void testNumWaysToClimb() {
        int[] allowedSteps = new int[] {1,2};
        int numStairs = 5;
        System.out.println("Number of ways to climb "+numStairs+" stairs using allowed steps: "+Arrays.toString(allowedSteps)+ " is "+numWaysToClimb(allowedSteps, numStairs));
        allowedSteps = new int[] {5};
        numStairs = 5;
        System.out.println("Number of ways to climb "+numStairs+" stairs using allowed steps: "+Arrays.toString(allowedSteps)+ " is "+numWaysToClimb(allowedSteps, numStairs));
        allowedSteps = new int[] {3};
        numStairs = 5;
        System.out.println("Number of ways to climb "+numStairs+" stairs using allowed steps: "+Arrays.toString(allowedSteps)+ " is "+numWaysToClimb(allowedSteps, numStairs));
        allowedSteps = new int[] {2,3,4};
        numStairs = 5;
        System.out.println("Number of ways to climb "+numStairs+" stairs using allowed steps: "+Arrays.toString(allowedSteps)+ " is "+numWaysToClimb(allowedSteps, numStairs));
    }
    public static void testMaxDigitsKeyPad() {
        int numDigits = 10;
        int startDigit=1;
        for(int digits = 1; digits<=numDigits; digits++) {
            System.out.println(maxDigitsKeyPad(digits, 1) + " combinations for "+ digits+" starting at 1:" );
        }
    }
    public static void main(String[] args) {
//        testfibn();
 //       testSumWays();
 //       testMinCoinSum();
//        testMaxIncrSequence();
//        testKnapsack();
//        testKnapsackNoRep();
//          testLongestSubSequence();
//        testLongestSubSequence2();
//        testLongestSubSequenceFinal();//
//        testEvenOdd();
//        testBreakIntoWords();
//        testGetMaxCoinVal();
 //       testGetMaxCoinValGeeks();
//        testGetMaxCoinValMine();
//        testMaxSizeSubMatrix();
//        testMaxDigitsKeyPad();
//        testNumWaysToClimb();
        testMinCoinsForMakingChange();
    }
}
