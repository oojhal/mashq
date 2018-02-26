package icf;

/**
 * Created by ssiddiqu on 2/23/18.
 */
public class StringsProblems {
    public static final String SP=" ";
    public static void printSpiral(String[][] input) {
        if((input==null)||(input.length==0))
            return;
        int rs = 0;
        int re = input.length-1;
        int cs = 0;
        int ce = input[0].length-1;
        int numElems = input.length * input[0].length;
        int visitedElems = 0;
        while(visitedElems < numElems) {
            // at top go all the way right
            for (int ci = cs; ci <= ce; ci++) {
                System.out.print(input[rs][ci]+SP);
                visitedElems++;
            }
            if(visitedElems >= numElems)
                break;
            // first row got done
            rs = rs+1;
            // at last column move all the way down
            for(int ri = rs; ri<= re; ri++) {
                System.out.print(input[ri][ce]+SP);
                visitedElems++;
            }
            if(visitedElems >= numElems)
                break;
            // last column got done
            ce--;
            // at last row move from right to all the way left
            for(int cd = ce; cd >= cs; cd--) {
                System.out.print(input[re][cd]+SP);
                visitedElems++;
            }
            if(visitedElems >= numElems)
                break;
            // last row got done
            re--;
            // at first column move from bottom row to top
            for(int rd =re; rd>= rs; rd--) {
                System.out.print(input[rd][cs]+SP);
                visitedElems++;
            }
            if(visitedElems >= numElems)
                break;
            cs++;
        }
    }
    public static void testPrintSpiral() {
        String[][] input = new String[][] {
            {"my", "name", "now", "is"},
            {"is","all","right","sarim"},
            {"this","and",",", "siddiqui"}
        };
        printSpiral(input);
    }
    public static void main(String[] args){
        testPrintSpiral();
    }
}
