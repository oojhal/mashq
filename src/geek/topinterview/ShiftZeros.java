package geek.topinterview;
import java.util.Arrays;
/**
 * Created by ssiddiqu on 4/10/18.
 */
public class ShiftZeros {
    /**
     * Given a list of numbers, shift all the zeros to the right while maintaining the order of non zero
     * numbers
     * e.g. change 3,4,0,9,0,0,5,8  to 3,4,9,5,8,0,0,0
     * @param nums
     */
    public static int[] shiftZeros(int[] nums) {
        // index of the first zero
        int zStart=0;
        // index of the last zero
        int zEnd=0;
        while((zStart<nums.length)&&(nums[zStart]!=0)) {
            zStart++;
        }
        // if there are still elements in the list
        if(zStart<nums.length) {
            zEnd = zStart;
            // scan till the end of the array
            while(zEnd<nums.length) {
                // keep incrementing zEnd until find a non zero element
                if(nums[zEnd]==0) {
                    zEnd++;
                }
                //nums[zEnd] is non zero
                else {
                    // need to swap nums[zEnd] with the zStart
                    nums[zStart] = nums[zEnd];
                    nums[zEnd]=0;
                    // increment the indices
                    zStart++;
                    zEnd++;
                }
            }
        }
        return nums;
    }
    public static void testShiftZeros() {
        int[] nums = new int[] {3,4,0,9,0,0,5,8,2};
        System.out.println(Arrays.toString(shiftZeros(nums)));
        nums = new int[] {3,4,9,5,8,2};
        System.out.println(Arrays.toString(shiftZeros(nums)));
        nums = new int[] {0,3,4,9,5,8,2};
        System.out.println(Arrays.toString(shiftZeros(nums)));
        nums = new int[] {0,0,0,0,0,0};
        System.out.println(Arrays.toString(shiftZeros(nums)));
    }
    public static void main(String[] args) {
        testShiftZeros();
    }
}
