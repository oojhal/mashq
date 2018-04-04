package interviews;

/**
 * Created by ssiddiqu on 3/15/18.
 */
import java.util.Arrays;
public class TwoDSort {
    public static void twoDSort(int[][] arr) {
        // elements before sortedI are already sorted
        // sortedI starts with the last element of the twoD array
        int[] sortedI = new int[]{arr.length - 1, arr[arr.length - 1].length-1};
        int[] lastSort = new int[] {0,0};
        //sortedI moves from end to the beginnning
        while(compare(sortedI,lastSort)> 0) {
            // have as many iterations as the number of elements
                // in each iteration move the biggest element to the one before
                // the sorted index
                int[] currIndx = new int[]{0, 0};
                // currIndx moves from 0,0 to the sortedI
                while (compare(currIndx, sortedI) < 0) {
                    int[] nextIndex = increase(arr, currIndx);
                    if (arr[currIndx[0]][currIndx[1]] > arr[nextIndex[0]][nextIndex[1]]) {
                        // swap the elements at arr[sRow][sCol] with the next element
                        int tmp = arr[nextIndex[0]][nextIndex[1]];
                        // saved the value of next element so can assign current element to next one
                        arr[nextIndex[0]][nextIndex[1]] = arr[currIndx[0]][currIndx[1]];
                        arr[currIndx[0]][currIndx[1]] = tmp;
                        // bigger element will b at nextIndex[0] row and nextIndex[1] col
                    }
                    currIndx = nextIndex;
                }
                decrease(arr, sortedI);
        }
    }
    private static int[] increase(int[][] arr, int[] currIndx) {
        // curr[0] is row index, curr[1] is col index
        // arr[curr[0]] gives the current row, arr[curr[0]].length
        // provides the number of columns in the current row
        int[] newIndex = new int[2];
        newIndex[0] = currIndx[0];
        newIndex[1] = currIndx[1]+1;
        if(newIndex[1]>= arr[currIndx[0]].length) {
            // already reached the last column
            // go to the next row, first column
            newIndex[0] = currIndx[0]+1;
            newIndex[1] = 0;

        }
        return newIndex;
    }
    private static int compare(int[] indices1, int[] indices2) {
        int ret =0;
        ret  = Integer.compare(indices1[0], indices2[0]);
        if(ret==0) {
            ret = Integer.compare(indices1[1], indices2[1]);
        }
        return ret;
    }
    private static void decrease(int[][] arr, int[] indx) {
        indx[1]--;
        if(indx[1]< 0) {
            indx[0]--;
            indx[1] = arr[indx[0]].length-1;
        }
    }
    public static void testtwoDSort() {
        int[][] arr = new int[][] {{5,4,3},{10,34,99},{2,17,9}};
        twoDSort(arr);
        Arrays.stream(arr).forEach((row)-> System.out.println(Arrays.toString(row)));
     }
     public static void main(String[] args) {
        testtwoDSort();
     }
}
