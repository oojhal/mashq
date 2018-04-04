package algo;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 3/13/18.
 * Find the smallest range that contains at least one element from each list
 * https://www.youtube.com/watch?v=Fqal25ZgEDo
 */
public class SmallestRange {
    /**
     * keep a current index of element in each list in next[].
     * next[] starts with all first element in each list
     * keep track of the minimum range so far which is sRangeMin - sRangeMax
     * next[i] is the index of the current element in the ith list
     * min_queue is a heap of indices. top index corresponds to the
     * min_i is the value corresponding to the index
     * @param nums
     * @return
     */
    public static int[] smallestRange(int[][] nums) {
        // keep track of the smallest range so far
        int sRangeMin = 0, sRangeMax = Integer.MAX_VALUE;
        // keeps track of the max in the current range
        int currMax = Integer.MIN_VALUE;
        // next[i] is the current position in the ith list
        int[] next = new int[nums.length];
        boolean flag = true;
        // nums[i][next[i]] is next[i] th element in the ith list
        // the element being inserted is i which represents an index into the list
        // nums[i][next[i] represents the value in the ith list at next[i] index
        // The number i being inserted goes to the top of the queue if it corresponds to the
        // smallest elememt
        // nums[i][next[i]] and nums[j][next[j]] goes to the top of the queue
        //
        PriorityQueue< Integer > min_queue = new PriorityQueue < Integer > ((i, j) -> nums[i][next[i]] - nums[j][next[j]]);
        // next will have all 0's
        // iterate over all the lists
        for (int i = 0; i < nums.length; i++) {
            // next[i] = 0 for all i's that means nums[i][next[i]] will return the zeroth element
            // number 'i' will be inserted at a position which corresponds to the order of zeroth element of list i
            // integer index represening the list that contains the smallest zeroth element will be at the front
            // of the queue
            min_queue.offer(i);
            // find the maximum value at zeroth elements of all lists
            currMax = Math.max(currMax, nums[i][0]);
        }
        // max will contain the highest 0th element in all the lists
        // minimum 0th element will be at the top of the heap
        // go through all the lists
        for (int i = 0; i < nums.length && flag; i++) {
            // j iterates over the ith list so it goes from
            // 0 to the number of elements in the ith list
            for (int j = 0; j < nums[i].length && flag; j++) {
                // find the list number of the current smallest element in heap
                int min_i = min_queue.poll();
                // current maximium is max
                // current smallest element is nums[min_i][next[min_i]]
                // miny - minx is the smallest range prior so far
                // if the current range is smaller than the smallest range so far
                if (sRangeMax - sRangeMin > currMax - nums[min_i][next[min_i]]) {
                    sRangeMin = nums[min_i][next[min_i]];
                    sRangeMax = currMax;
                }
                // increment the next index counter for the min_i th list forward
                next[min_i]++;
                // if reached the end of the ith list
                if (next[min_i] == nums[min_i].length) {
                    flag = false;
                    break;
                }
                // add the new element from list min_i to the heap
                min_queue.offer(min_i);
                currMax = Math.max(currMax, nums[min_i][next[min_i]]);
            }
        }
        return new int[] { sRangeMin, sRangeMax};
    }
    public static void testSmallestRange() {
        int[][] lists = new int[][] {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};

        System.out.println(Arrays.toString(smallestRange(lists)));
    }
    public static void priorityQExample() {
        // str1 is the element being inserted, str2 represents all the current elements
        // lesser of str1 and str2 is the higher priority
        // str1.length - str2.length return -1 if str1.length < str2.length.
        // str1 is the highest priority
        PriorityQueue<String> queue = new PriorityQueue<>((str1,str2) -> str1.length()-str2.length());
        queue.add("short");
        queue.add("very long indeed");
        queue.add("medium");
        while (queue.size() != 0)
        {
            System.out.println(queue.remove());
        }

    }
    public static void main(String[] args) {
        //priorityQExample();
        testSmallestRange();
    }

}
