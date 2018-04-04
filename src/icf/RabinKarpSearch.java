package icf;

/**
 * Created by ssiddiqu on 3/26/18.
 */
public class RabinKarpSearch {
    private static int seed = 101;
    public static int patternIndx(String src, String pattern) {
        // src = fggdjabc pat=abc
        if(src.length()<pattern.length())
            return -1;
        int patHash = calculateHash(pattern, 0, pattern.length()-1);
        int retIndx=-1;
        int rollHash= 0;
        for(int srcIndx=0; srcIndx+pattern.length() <= src.length(); srcIndx++) {
            if(srcIndx==0) {
                // calculating for the first time
                rollHash = calculateHash(src,0, pattern.length()-1);
            }
            else {
                // remove the hash of the first character
                rollHash = rollHash- src.charAt(srcIndx-1);
                // divide remaining by seed
                rollHash = rollHash/seed;
                // add hash of the last character
                rollHash += src.charAt(srcIndx+pattern.length()-1) * Math.pow(seed,pattern.length()-1);
            }
            if((rollHash==patHash)&&(src.substring(srcIndx,srcIndx+pattern.length()).equals(pattern))) {
                retIndx = srcIndx;
                break;
            }
        }
        return retIndx;
    }
    private static int calculateHash(String str, int start, int end) {
        // perform error checks for invalid inputs
        int hash=0;
        for(int i=start; i<=end; i++) {
          // for i==0   will be 101^0 = 1;
         hash += str.charAt(i) * Math.pow(seed,i-start);
        }
        return hash;
    }
    public static void testPatternIndx() {
        String src="fhghggabc";
        String pattern = "abc";
        System.out.println("Pattern "+pattern+" occurs in source "+src+" at index "+patternIndx(src, pattern));
    }
    public static void main(String[] args) {
        testPatternIndx();
    }
}
