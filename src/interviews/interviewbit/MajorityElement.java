package interviews.interviewbit;

/**
 * Created by ssiddiqu on 6/4/18.
 * Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Example :

 Input : [2, 1, 2]
 Return  : 2 which occurs 2 times which is greater than 3/2.
 */
public class MajorityElement {
    public static int findMaj(int[] list) {
        int majE = list[0];
        int majC = 1;
        for(int j=1; j< list.length; j++) {
            if(list[j]==majE) {
                majC++;
            }
            else {
                majC--;
            }
            if(majC==0) {
                majE = list[j];
                majC= 1;
            }
        }
        return majE;
    }
    public static void majETest() {
        int[] tArr = {1,2,3,4,2,2,2,1,1};
        System.out.println(findMaj(tArr));
    }
    // can be 1,2,3,4,1,1
    // start scanning. 1,2,1,3  2,1,3,1  2,1,3,2
    // can you avoid keeping track of both 2 and 1?
    // can you avoid counting
    public static void main(String[] args) {
        majETest();
    }
}
