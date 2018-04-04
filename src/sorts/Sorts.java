package sorts;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
/**
 * Created by ssiddiqu on 3/13/18.
 */
public class Sorts {
    public static void bubble(int[] arr) {
        // iteration 1 largest element is at index n-1
        // iteration 2, sorted from n-2..n-1
        // iteration 3, sorted from n-3..n-1
        // sBegin goes to the end of the array. it keeps track
        // of the start of the sorted sub array
        for(int sBegin =arr.length-1; sBegin> 0; sBegin--) {
            // mv is the start of the unsorted subarray
            // in each iteration it goes to till the beginning of the
            // sorted array
            for(int mv=0; mv < sBegin; mv++) {
                if(arr[mv]>arr[mv+1]) {
                    // current element is bigger than the next element, swap them
                    int tmp = arr[mv+1];
                    arr[mv+1] = arr[mv];
                    arr[mv] = tmp;
                    // now the bigger element is at indx mv+1
                }
            }

        }
    }
    public static void insert(int[] arr) {
        for(int unSortStart = 1; unSortStart < arr.length; unSortStart++) {
            // sorted end is unSortStart-1
            // valI is the value to be inserted
            int valI = arr[unSortStart];
            // keep comparing the value to be inserted with the elements on the left
            // starting with element unSortStart-1
            int shiftB = unSortStart-1;
            while((shiftB>= 0)&&(valI< arr[shiftB])) {
                shiftB--;
            }
            //  valI >= arr[shiftB] so valI must go right after shiftB
            shiftB++;
            // shiftB is the index where valI must be inserted.
            // Everything from shiftB+1 till unSortStart must be shifted right
            // start from unSortStart, the value at unSortStart is saved in valI
            // copy the left value at shiftIndx-1 to the right value shiftIndx
            // loop ends when shiftInd == shiftB so the last shiftIndx value for
            // which the loop runs is shiftB+1
            for(int shiftIndx = unSortStart ; shiftIndx > shiftB; shiftIndx-- ) {
                arr[shiftIndx] = arr[shiftIndx-1];
            }
            arr[shiftB]=valI;
        }
    }
    public static int[] mergeSort(int[] arr) {
       return mergeSort(arr, 0, arr.length-1);
    }
    private static int[] mergeSort(int[] src, int start, int end) {
        if(start==end) {
            return new int[]{src[start]};
        }
        else {
            int numHalf = (end-start+1)/2;
            // e.g. start =1, end =2 mid =(2-1+1)/2 =1  sort1(1,1), sort2 = 2,2)
            // need to split into two
            // start =0, end =1 mid (1-0+1)/2 = 1, sort1(0,0) sort2(
            int[] sort1 = mergeSort(src, start, start+numHalf-1);
            int[] sort2 = mergeSort(src,start+numHalf , end);
            return merge(sort1, sort2);
        }
    }
    private static int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] merged = new int[len1+len2];
        int indx1=0;
        int indx2=0;
        int indx1Last = Integer.MAX_VALUE;
        int indx2Last= Integer.MAX_VALUE;
        while((indx1+indx2 < merged.length)) {
            // in each iteration find the smaller element
            if(indx2>=arr2.length) {
                indx2Last= Integer.MAX_VALUE;
            }
            else {
                indx2Last = arr2[indx2];
            }
            while((indx1<arr1.length)&& (arr1[indx1]<=indx2Last)) {
                merged[indx1+indx2] = arr1[indx1];
                indx1++;
            }
            if(indx1>=arr1.length) {
                indx1Last= Integer.MAX_VALUE;
            }
            else {
                indx1Last = arr1[indx1];
            }
            while((indx2<arr2.length)&&(arr2[indx2]<indx1Last)) {
                merged[indx1+indx2] = arr2[indx2];
                indx2++;
            }

        }
        return merged;
    }

    /**
     * This merge sort implementation performs in place merge
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        mergeSort2(arr, 0, arr.length-1);
    }
    private static void mergeSort2(int[] src, int start, int end) {
        // if there is more than one element to sort
        if(start<end) {
            int numHalf = (end-start+1)/2;
            // e.g. start =1, end =2 mid =(2-1+1)/2 =1  sort1(1,1), sort2 = 2,2)
            // need to split into two
            // start =0, end =1 mid (1-0+1)/2 = 1, sort1(0,0) sort2(
            mergeSort2(src, start, start+numHalf-1);
            mergeSort2(src,start+numHalf , end);
            merge2(src, start, start+numHalf-1, start+numHalf, end);
        }
    }

    /**
     * arr contains two sorted subarrays
     * sorted array1 = from start1 to end1
     * sorted array2= from start2 to end2
     * This method sorts arr in place without requiring extra space
     * Sorts the elements between start21 and end2
     * @param arr
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     */
    private static void merge2(int[] arr, int start1, int end1, int start2, int end2) {
        // arr contains the full array with two sorted subarrays
        // 2,4,6,8: 1,3,5,7
        int len1 = end1-start1+1;
        int len2 = end2-start2+1;
        int totalLen = len1+len2;
        // elements before mergedI are already sorted
        int mergedI=0;
        while(mergedI <=  end2) {
            // scan through first sub array until reach an element bigger than second sub array
            // keeping moving start1 forward until find an element bigger than arr[start1]
            while((start1<=end1)&&(arr[start1]<arr[start2])) {
                start1++;
                mergedI++;
            }
            // got through first set completely, second set is already ordered
            if(start1> end1) {
                break;
            }
            // not crossed the end index of first subrarray. The element at first subarray is bigger than element in second
            // start1 <= end1, arr[start1]>= arr[start2]
            // copy the smaller element from the second subarray at the index of the first subrray and move everything to the right
            // element at arr[start2] must be copied to arr[start1]
            while((start2<= end2)&&(arr[start2]<= arr[start1])) {
                // save the second subarray smaller arr[start2] element
                // this needs to be inserted at first subrray index
                // there is a hole at start2 so move the element at start2-1 to start2, start2-2 to start2-1...start1 to start1+1
                // i.e. everything between start1 and start2 is shifted right. This creates a hole at start1.
                // the elements from start1 to start2-1 to the right one
                int insert= arr[start2];
                int cntr = start2 -1;
                while(cntr >= start1) {
                    arr[cntr+1]= arr[cntr];
                    cntr--;
                }
                // put the smaller element from second array in the first array
                arr[start1] = insert;
                // all the indices shifted right by 1
                start2++;
                mergedI++;
                start1++;
                end1++;

            }
            if(start2> end2) {
                break;
            }
            // start2< end2 and arr[start2]>= arr[start1]
        }

    }
    public static void quickSort(int[] input) {
        quickSort(input, 0, input.length-1);
    }
    public static void  quickSort(int[] input, int bIndx, int endIndx) {
        if(bIndx<endIndx) {
             int pivotIndx = partition(input,bIndx,endIndx);
             // the element at the pivot is at the right position
             quickSort(input, bIndx, pivotIndx-1);
             quickSort(input, pivotIndx+1, endIndx);
        }
    }
    public static int partition(int[] input, int bIndx, int endIndx) {
        // move the smaller elements to the left of the pivot and
        // bigger elements to the right of the pivot
        // make last element the pivot
        int pivot = input[endIndx];
        //smallIndx keeps track of the last smallest element
        int smallIndx = bIndx;
        for(int curr= bIndx; curr<= endIndx; curr++) {
            // if an element less or equal to the pivot found
            // last element for which this is true is the pivot itself
            if(input[curr]<=pivot) {
                // the current element is smaller than pivot
                // swap with the element at smallIndx
                int tmp = input[smallIndx];
                input[smallIndx] = input[curr];
                input[curr] = tmp;
                // smallIndx is incremented only if element smaller
                // than pivot is found
                smallIndx++;
            }
        }
        // curr = endIndx+1, scanned through the whole array and
        // smallIndx index is one more than the last smallest or equal element
        // which is  the pivot itself
        return smallIndx-1;
    }
    public static void testSorts() {
        int[] arr = new int[] {10,4,12,67,15,2,4,9};
        System.out.println("UnSorted array" +Arrays.toString(arr));
        bubble(arr);
        System.out.println("Sorted array Bubble:" +Arrays.toString(arr));
        arr = new int[] {10,4,12,67,15,2,4,9};
        insert(arr);
        System.out.println("Sorted array Insert: " +Arrays.toString(arr));
        arr = new int[] {10,4,12,67,15,2,4,9};
        System.out.println("Sorted array Merge: " +Arrays.toString(mergeSort(arr)));
        arr = new int[] {10,4,12,67,15,2,4,9};
        quickSort(arr);
        System.out.println("Sorted array Quick: " +Arrays.toString(arr));
        arr = new int[] {10,4,12,67,15,2,4,9};
        mergeSort2(arr);
        System.out.println("Sorted array MergeSort2: " +Arrays.toString(arr));

    }
    public static void main(String[] args) {
        testSorts();
    }
}
