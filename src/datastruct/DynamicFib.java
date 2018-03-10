package datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssiddiqu on 1/27/18.
 */
public class DynamicFib {
    static int callCnt=0;
    public static int fib(int n) {
        int ret=0;
        if(n<=0)
            ret= 0;
        else if(n==1)
            ret = 1;
        else
            ret= fib(n-1)+fib(n-2);
        callCnt++;
        return ret;

    }
    public static int fibDyn(int n, Map<Integer, Integer> result) {
        int ret= 0;
        if(result.get(n) !=null)
            ret= result.get(n);
        else if(n<=0)
            ret= 0;
        else if(n==1)
            ret = 1;
        else
             ret = fibDyn(n-1, result)+fibDyn(n-2, result);
        result.put(n,ret);
        callCnt++;
        return ret;
    }
    public static void main(String[] args) {
        for(int i=0; i<20;i++) {
            callCnt=0;
            System.out.println(fib(i) +" took "+callCnt+" times");
        }
        System.out.println(" ************* Dynamic Fib  *********************");
        Map<Integer,Integer> resultMap = new HashMap<>();
        for(int i=0; i<20;i++) {
            resultMap.clear();
            callCnt=0;
            System.out.println(fibDyn(i,resultMap) +" took "+callCnt+" times");
        }
    }
}
