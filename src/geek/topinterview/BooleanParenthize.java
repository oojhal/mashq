package geek.topinterview;

/**
 * Created by ssiddiqu on 3/24/18.
 * Given a boolean expression with following symbols.

 Symbols
 'T' ---> true
 'F' ---> false
 And following operators filled between symbols

 Operators
 &   ---> boolean AND
 |   ---> boolean OR
 ^   ---> boolean XOR
 Count the number of ways we can parenthesize the expression so that
 the value of expression evaluates to true.
 Example:
 Input: symbol[]    = {T, F, T}
 operator[]  = {^, &}
 Output: 2
 The given expression is "T ^ F & T", it evaluates true
 in two ways "((T ^ F) & T)" and "(T ^ (F & T))"
 */
public class BooleanParenthize {
    public static int booleanParenth(int[] symbols, char[] expr) {
        //start analyzing the expression one char at a time
        //iterate over. if an operator is found,
        int exprl = expr.length;
        int[][] Ts = new int[expr.length][exprl];
        int[][] Fs = new int[expr.length][exprl];
        // for single character
        for(int ind=0; ind < expr.length; ind++) {
            Ts[ind][ind] = (expr[ind]=='t')? 1:0;
            Fs[ind][ind] = (expr[ind]=='f')? 1:0;
        }
        // At each character in expression build an expression
        // window around it
        // need to calculate T[0][expr.length-1]
        // last index when adding gap should be length-1
        for(int gap = 1; gap < exprl; gap++) {
            // window end index eIndx should be less than exprl which is the
            // last index of the expression
            for(int sIndx=0, eIndx=sIndx+gap; eIndx<exprl; sIndx++) {
                int tCnt =0;
                int fCnt =0;
                // need to calculate T[sIndx][eIndx]
                for(int k=sIndx; k<= eIndx; k++) {
                    // T(sIndx,k) T(k+1,eIndx)
                    // split happens at sIndx+k i.e.
                    // sIndx, sInd+1, sIndx+2 ... eIndx
                    int splt = sIndx+k;
                    int tsk = Ts[sIndx][k]+Fs[sIndx][k];
                    int tke = Ts[k+1][eIndx]+Fs[k+1][eIndx];

                }
            }
        }
        return 0;
    }

}
