package geek.topinterview;

/**
 * arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
 * Created by ssiddiqu on 3/26/18.
 */
public class RotatedArraySearch {
    public static int search(int[] arr, int num) {
        return search(arr, num, 0, arr.length - 1);
    }

    /**
     * searches for 'num' in 'arr', from 'start' index to 'end'
     *
     * @param arr
     * @param start
     * @param end
     * @param num
     * @return
     */
    private static int search(int[] arr,  int num, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start + 1) / 2;
        // check for the middle element
        // when start== end, mid = start+(start-start+1)/2 = start+1/2 = start
        if (arr[mid] == num) {
            return mid;
        }
        // already checked that arr[start] != num
        if(start==end) {
            return -1;
        }
        // mid already checked
        // split into start, mid-1 and mid+1 -> end
        if (arr[mid-1] > arr[start]) {
            // if number is in the first half
            if ((num <= arr[mid-1]) && (num >= arr[start])) {
                return search(arr, num, start, mid-1);
            }
            // first half is sorted and number is not in the first half
            // number is in unsorted second half
            else {
                return search(arr,num,mid+1,end);
            }
        }
        // second half is sorted
        else {
            if ((num >= arr[mid+1]) && (num <= arr[end])) {
                return search(arr, num,mid + 1, end);
            }
            else {
                return search(arr, num,start, mid-1);
            }
            // second half is sorted but the number is not there


        }
    }
    public static void searchTest() {
        int arr[] = new int[] {5, 6, 7, 8, 9, 10, 1, 2, 3};
        System.out.println(search(arr,5));
        System.out.println(search(arr,2));

    }
    public static void main(String[] args) {
        searchTest();
    }
}
