package geek.topinterview;

import java.util.Scanner;

/**
 * Created by ssiddiqu on 3/29/18.
 */
public class WaysToMakeChange {
    public static long numWays(int[] coins, int val) {
        long[] ways = new long[val + 1];
        // number of ways to make 0 dollar is 1
        // base case
        ways[0] = 1;

        // keeps track of how many coins can be used
        for (int cIndx = 0; cIndx < coins.length; cIndx++) {
            //ways contains the number of ways to make each value using coins[0]...coins[cIndx-1]
            // coins
            int numWays = 0;
            // try to see how each value can be made by using coin at CIndx
            for (int cVal = 0; cVal <= val; cVal++) {
                if (cVal - coins[cIndx] >= 0) {
                    ways[cVal] += ways[cVal - coins[cIndx]];
                }

                // go through each coin
            }

        }
        return ways[val];
    }
    public static void main(String[] args) {
        int[] coins2 = {41,34,46,9,37,32,42,21,7,13,1,24,3,43,2,23,8,45,19,30,29,18,35,11};
        int val2= 250;
        System.out.println(numWays(coins2,val2));
        Scanner in = new Scanner(System.in);
        int val = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for (int coins_i = 0; coins_i < m; coins_i++) {
            coins[coins_i] = in.nextInt();
        }
        // array represents number of ways to make each amount from 0-val
        // ways[i] stores the number of ways to make value i

        System.out.println(numWays(coins,val));


    }

}