package interviews.interviewbit;

import java.util.List;
import java.util.Arrays;

import static java.lang.StrictMath.max;

/**
 * Created by ssiddiqu on 6/3/18.
 */
public class MaxSumSub {
    public static  int maxSubArray(List<Integer> A) {
        int maxsum = A.get(0) , cursum = A.get(0) ;
        for(int i=1;i<A.size();i++)
        {
            cursum = max(cursum+A.get(i),A.get(i));
            maxsum = max(maxsum,cursum);
        }
        return maxsum;
    }
    public static void maxSubTest() {
        List<Integer> ip = Arrays.asList(-384, -410, 23, -491, -201, -406, -187, -423, -62, -307, -225, -202, -190, -381,
            -421, -147, -21, -361, -399, -169, -173, -318, 58, -287, -496, -227, -257, -445, -135, -411, -154, -298, -1, -7, -200,
            -316, -180, -337, -493, -106, -66, -106, -400, 49, -149, -141, -4, -93, -439, 86, -435, -429, -309, -305, 6, -388, -154,
            -111, -205, -323, -234, 97, -107, -414, -194, -19, -241, -345, 47, -223, -87, -34, 85, -269, 70, -46, -359, -499, 14, -165,
            -396, -305, -240, -367, -500, -448, -316, -85, -3, 66, -356, -294, -195, -27, -136, -269, -62, -54, -398, 62, -418, -144, 0,
            -276, -416, -333, -418, -424, 80, -320, 2, -107, -296, -4, 8, -333, -168, -413, -420, 46, -489, -481, -187, 91, -304, -124,
            -117, -207, -480, -374, -408, -316, -145, -291, -25, -456, -77, -266, -87, -215, 57, -484, -237, 29, -118, -391, -242, -121,
            -168, -16, -391, -131, -294, -432, -356, -478, -377, -301, -256, -216, -187, -122, -1, 45, -1, 79, -342, -300, -395, -17, -228,
            -379, -475, 14, -499, -451, -23, -219, -345, -237, -288, -142, -22, -275, -478, -133, 54, -296, -21, -276, -487, -100, -173,
            -3, -306, -375, -196, 94, -88);
        List<Integer> ip2 = Arrays.asList(-163, -20);
        List<Integer> ip3 = Arrays.asList(-89, -277, -475, -480, -420);
        List<Integer> ip4 = Arrays.asList(-89);
        List<Integer> ip5 = Arrays.asList(-89,89);
        System.out.println(maxSubArray(ip));
    }
    public static void main(String[] args) {
        maxSubTest();
    }
}
