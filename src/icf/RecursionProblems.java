package icf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ssiddiqu on 2/20/18.
 */
public class RecursionProblems {
    public static double pow(double base, int pow) {
        if(pow < 0) {
            base = 1/base;
            pow = pow*-1;
        }
        return posPow(base, pow);
    }
    public static double posPow(double base, int pow) {
        if(pow == 0) {
            return 1;
        }
        else if(pow ==1) {
            return base;
        }
        else
          return (base*posPow(base,pow-1));
    }
    public static double powEff(double base, int pow) {
        if(pow < 0) {
            base = 1/base;
            pow = pow*-1;
        }
        double[] res = new double[pow+1];
        return posPowEff(base, pow, res);
    }
    public static double posPowEff(double base, int pow, double[] res) {
        if(Double.compare(res[pow],0) > 0)
            return res[pow];
        if(pow == 0) {
            res[pow] = 1;
        }
        else if(pow ==1) {
            res[pow]=base;
        }
        else {
            int hpow = pow/2;
            int othpow = pow-hpow;
            res[pow] = posPowEff(base,hpow,res)*posPowEff(base,othpow, res);
        }
        return res[pow];
    }

    /**
     * Copied from stackoverflow but does not work
     * @param base
     * @param exp
     * @return
     */
    static int ipow(int base, int exp)
    {
        int result = 1;
        while (exp!=0)
        {
            if (exp == 1)
                result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
    public static void testipow() {
        double[] bases= new double[]{4,5,10,1};
        System.out.println(ipow(2,6));
        for(int pow = -5; pow< 5; pow++) {
            for(int i=0;i<bases.length; i++) {
                long t1=System.currentTimeMillis();
                double res = pow(bases[i],pow);
                long t2=System.currentTimeMillis();
                System.out.println(bases[i]+" ^ "+pow+"= "+res+"in "+(t2-t1)+" ms");
                t1=System.currentTimeMillis();
                res = powEff(bases[i],pow);
                t2=System.currentTimeMillis();
                System.out.println(bases[i]+" ^ "+pow+"="+res+"in "+(t2-t1)+" ms");
            }
        }
        System.out.println("**************************************");
        double base =5;
        int pow = 40;
        long t1=System.currentTimeMillis();
        double res = pow(base,pow);
        long t2=System.currentTimeMillis();
        System.out.println(base+" ^ "+pow+"= "+res+"in "+(t2-t1)+" ms");
        t1=System.currentTimeMillis();
        res = powEff(base,pow);
        t2=System.currentTimeMillis();
        System.out.println(base+" ^ "+pow+"="+res+"in "+(t2-t1)+" ms");
    }

    public static List<List<Integer>> findSets(List<Integer> source) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(source.size()==0) {
            //add empty set once
            ret.add(new ArrayList<Integer>());
        }
        //source.size() > 0
        else {
            Integer elem = source.remove(0);
            // get the list of sets containing the remaining n-1 elements
            // this will return empty set as well
            List<List<Integer>> otherSets = findSets(source);
            for(List<Integer> oneS:otherSets) {
                ret.add(oneS);
                List<Integer> moreS = new ArrayList<>(oneS);
                moreS.add(elem);
                ret.add(moreS);
            }
        }
        return ret;
    }
    public static void testfindSets() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1,2));
        System.out.println("Sets for:"+input);
        System.out.println(findSets(input));
        input = new ArrayList<>(Arrays.asList(1));
        System.out.println("Sets for:"+input);
        System.out.println(findSets(input));
        input = new ArrayList<>();
        System.out.println("Sets for:"+input);
        System.out.println(findSets(input));
        input = new ArrayList<>(Arrays.asList(1,3,4,5,6,7));
        System.out.println("Sets for:"+input);
        System.out.println(findSets(input));
    }

    /**
     * Input: 10?
     Output: 101, 100
     i.e. ? behaves like a wild-card. There are two possibilities for 10?, when that ? is replaced with
     either 0 or 1.
     Input: 1?0?
     Output: 1000, 1001, 1100, 1101
     Please write a program that takes given strings as input and produces the suggested output.
     Suggested time: 20 minutes.

     */
    public static List<String> wildCard(String input, String wc) {
        List<String> ret = new ArrayList<>();
        if(input.isEmpty()) {
            ret.add("");
        }
        else {
            int wcl = input.indexOf(wc);
            // if not wild card present then only current String is the answer
            if (wcl == -1) {
                ret.add(input);
            } else {
                // get the substring upto the wild card
                // wcl is the index at which wild card is present
                // get the string upto one character before the wild card
                String first = input.substring(0, wcl);
                // if there are still more left in the string after the wild card
                String rem = "";
                if (input.length() > wcl + 1) {
                    rem = input.substring(wcl + 1);
                }
                    List<String> restL = wildCard(rem, wc);
                    for (String other : restL) {
                        // final string is current string without the wild card
                        ret.add(first + "0" + other);
                        ret.add(first + "1" + other);
                    }
                }
        }
        return ret;
    }
    private static void helper(int currRow, boolean[] cols, boolean[] diag1, boolean[] diag2,
                        String[] board, List<String[]> res) {
        // when currRow == board.length, all N queens have been placed so create the clone and add it in result
        if (currRow == board.length)
            res.add(board.clone());
        else {
            // iterate over all columns
            for (int currCol = 0; currCol < board.length; currCol++) {
                int id1 = currRow - currCol + board.length, id2 = 2*board.length - currRow - currCol - 1;
                if (!cols[currCol] && !diag1[id1] && !diag2[id2]) {
                    // create a new empty row
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    // place queen at column index of row
                    row[currCol] = 'Q';
                    // add new row with queen into the board
                    board[currRow] = new String(row);
                    cols[currCol] = true; diag1[id1] = true; diag2[id2] = true;
                    helper(currRow+1, cols, diag1, diag2, board, res);
                    cols[currCol] = false; diag1[id1] = false; diag2[id2] = false;
                }
            }
        }
    }

    public static void findSumNum(int[] nums, int target) {
        List<Integer> resSet = new ArrayList<>();
        if(findSumNum(nums,0, target, resSet)) {
            System.out.println("Found numbers "+ resSet+" to sum "+target);
        }
        else
            System.out.println("Could not find numbers that match to sum");
    }
    private static boolean findSumNum(int[] nums, int currInd, int leftSum, List<Integer> resultSet) {
         if(leftSum == 0) {
             return true;
         }
         else if(currInd>= nums.length)
             return false;
         for(int i=currInd; i<nums.length; i++) {
             leftSum = leftSum - nums[i];
             if(leftSum<0)
                 return false;
             else {
                 // add the current number to result set
                 resultSet.add(nums[i]);
                 // found solution
                 if(leftSum == 0)
                     return true;
                 // leftSum > 0
                 else {
                     if(findSumNum(nums,i+1,leftSum, resultSet))
                        return true;
                     else {
                         // remove the current num and try the next one
                         leftSum = leftSum + resultSet.remove(resultSet.size()-1);
                     }
                 }
             }
         }
         return false;
    }
    public static void findSumNumSets(int[] nums, int target) {
        List<List<Integer>> resSet = new ArrayList<>();
        resSet.add(new ArrayList<Integer>());
        findSumNumSets(nums, 0, target, resSet);
        if((resSet!=null)&&(resSet.size()>0)) {
            System.out.println("Found number sets for target "+ target);
            resSet.forEach((lst) -> System.out.println(lst));
        }
        else
            System.out.println("Could not find numbers that match to sum");
    }
    private static boolean findSumNumSets(int[] nums, int currInd, int leftSum, List<List<Integer>> resultSet) {
        // exhausted all the indices
        if(currInd>= nums.length)
            return false;
        // last list is the current one being used
        List<Integer> currResSet = resultSet.get(resultSet.size()-1);
        for(int i=currInd; i<nums.length; i++) {
            leftSum = leftSum - nums[i];
            if(leftSum<0)
                return false;
            else {
                // found solution
                if(leftSum == 0) {
                    currResSet.add(nums[i]);
                    List<Integer>  newResSet = new ArrayList<>(currResSet);
                    // make a copy and add the result set
                    resultSet.add(0, newResSet);
                    // remove the last added element
                    leftSum = leftSum+ currResSet.remove(currResSet.size()-1);
                    continue;
                    // return true;
                }
                    // leftSum > 0
                else {
                    currResSet.add(nums[i]);
                    if(!findSumNumSets(nums,i+1,leftSum, resultSet)) {
                        leftSum = leftSum + currResSet.remove(currResSet.size()-1);
                    }
                }
            }
        }
        if(currResSet.isEmpty()) {
            resultSet.remove(resultSet.size()-1);
        }
        return false;
    }
    public static List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<>();
        helper(0, new boolean[n], new boolean[2*n], new boolean[2*n],
            new String[n], result);
        return result;
    }
    public static void testNQueens() {
        List<String[]> sols = solveNQueens(4);
        sols.forEach((sl)-> {Arrays.stream(sl).forEach((one)->{System.out.println(one);
        });
        System.out.println("****************");});
    }
    public static void testWildCard() {
        String wc ="?";
        String input = "10"+wc;
        System.out.println("For string "+input+" result is "+wildCard(input,wc));
        input = "10"+wc+1+wc;
        System.out.println("For string "+input+" result is "+wildCard(input,wc));

    }
    public static void testFindSumNum() {
        int[] nums = new int[] {2,4,6,8};
        int sum = 16;
        findSumNum(nums, sum);
        nums = new int[] {2,4,6,8};
        sum = 15;
        findSumNum(nums, sum);
        nums = new int[] {2,4,6,8};
        sum = 20;
        findSumNum(nums, sum);

    }
    public static void testFindSumNumSets() {
        int[] nums = new int[] {2,4,6,8};
        int sum = 16;
        findSumNumSets(nums, sum);
        nums = new int[] {2,4,6,8};
        sum = 15;
        findSumNumSets(nums, sum);
        nums = new int[] {2,4,6,8};
        sum = 20;
        findSumNumSets(nums, sum);
        sum = 12;
        findSumNumSets(nums, sum);
    }
    static class Employee {
        String name;
        Employee manager;
        public Employee(String name, RecursionProblems.Employee manager) {
            this.name = name;
            this.manager = manager;
        }
    }
    public static String getManagerTrail(Employee emp) {
        if(emp.manager == null) {
            return emp.name;
        }
        else
        {
            return  getManagerTrail(emp.manager)+"-->"+emp.name;
        }
    }
    private static void testManagerTrail() {
        Employee jack = new Employee("Jack",null);
        Employee jill = new Employee("Jill",jack);
        Employee jim = new Employee("Jim",jill);
        Employee jane = new Employee("Jane",jim);

//it should return manager org
        System.out.println(getManagerTrail(jane));
    }
    public static void main(String[] args) {
 //       testipow();
 //       testfindSets();
//        testWildCard();
//          testNQueens();
//        testFindSumNum();
//        testFindSumNumSets();
        testManagerTrail();

    }
}
