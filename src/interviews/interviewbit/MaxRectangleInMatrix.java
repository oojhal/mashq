package interviews.interviewbit;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by ssiddiqu on 6/4/18.
 */
public class MaxRectangleInMatrix {
    // 0,0,1,2,2,1
    // 1,3,3,2,2
    // 1,1,1,1,1: 3,3,  2,2,2,2
    // if you encounter a number, everything below that will need to get updated
    // each number below has a different count
    // need the magnitude and count.
    /**
     * Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle containing all ones and return its area.

     Bonus if you can solve it in O(n^2) or less.

     Example :

     A : [  1 1 1
     0 1 1
     1 0 0
     ]

     Output : 4

     As the max area rectangle is created by the 2x2 rectangle created by (0,1), (0,2), (1,1) and (1,2)
     */
    public static int maxRectSize(int[][] matrix) {
        // number of columns is the maximum count of 1's
        int[] currSzs = new int[matrix[0].length];
        NavigableMap<Integer,Integer> area = new TreeMap<>();
        int maxA = 0;
        for(int i=0; i< matrix.length; i++) {
            for(int j=0; j< matrix[i].length; j++) {
                if(matrix[i][j]==0) {
                    currSzs[j] = 0;
                }
                else {
                    currSzs[j] = currSzs[j]+1;
                }
            }
            maxA = updateMax(currSzs, maxA, area);
            area.clear();
        }
        return maxA;
    }
    public static int updateMax(int[] runSz, int currMax, NavigableMap<Integer,Integer> sizeMap) {
        for(int sz: runSz) {
                // update areas for all entries in sizeMap that are smaller than sz
                for(int j=1; j<=sz; j++) {
                    sizeMap.merge(j, j, Integer::sum);
                    if(sizeMap.get(j)>=currMax) {
                        currMax = sizeMap.get(j);
                    }
                }
                // clear map for all entries bigger than sz
                NavigableMap<Integer,Integer>  bK =  sizeMap.tailMap(sz,false);
                bK.clear();
        }
        return currMax;
    }
    public static void testMaxRectSize() {
        int[][] matrx = new int[][] {
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0},
            {1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        };
        int[][] matrxo = new int[][] {

        };
        System.out.println(maxRectSize(matrx));
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(1);
        int[] iA = ar.stream().mapToInt(i->i).toArray();
        System.out.println(iA[0]);
    }
    public static void main(String[] args) {
        testMaxRectSize();
    }
}
