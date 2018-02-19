package algo;

/**
 * Created by ssiddiqu on 2/7/18.
 */
public class BitOperations {
    private String names[];
    private String[] names2;
    public static boolean getBit(int num, int ind) {
        // get a binary number with all 0's and 1 only at ind
        // start with 0000001 and left shit the one
//        System.out.println("Left shit 1 by "+ind+"  "+String.valueOf(1<<ind));
        return (((1<<ind)&num)!=0);
    }
    public static int setBit(int num, int ind) {
        // get a binary number with all 1's and 0 only at ind
        // 1&1 => 1, 0&1 => 0   b&1 = b; 0&b = 0;
        /**
         * to set means the result has to be 1 for both 0 and 1. And won't work as and with 0 will never set. Or with 1 will work. Or with
         * 0o0 = 0, 1o0 = bo0 = b. So or with all 0 and only 1 at the specified bit.
         */
        // start with 0000001 and left shit the one
        return ((1<<ind)|num);
    }
    public static int clearBit(int num, int ind) {
        // and with 0 will clear the bit, and with 1 will not affect the bit
        // 111101111
        // start with 11111111 and
        // 2^ ind
        /**
         * 2^0= 1  1 at 0th bit
         * 2^1=2  1 at 1st bit
         */
        // 1&1 => 1, 0&1 => 0   b&1 = b; 0&b = 0;
        /**
         * to set means the result has to be 1 for both 0 and 1. And won't work as and with 0 will never set. Or with 1 will work. Or with
         * 0o0 = 0, 1o0 = bo0 = b. So or with all 0 and only 1 at the specified bit.
         */
        // start with 0000001 and left shit the one
        return ((~(1<<ind))&num);
    }
    public static int updateBit(int num, int ind, boolean b) {
        return (b)?setBit(num,ind):clearBit(num,ind);
    }
    public static int clearMSBBits(int num, int ind) {
        /**
         * need  0000011111 and do an and
         */
        return (num&(-1>>>ind));
    }
    public static String bin(int i) {
        return String.valueOf(i)+"="+Integer.toBinaryString(i);
    }
    public static void main(String[] args) {
        int num=4;
        int i=0;
        System.out.println(bin(-1));
        System.out.println(bin(0));
        System.out.println("Get bit "+i+" of "+num+" = "+getBit(num,i));
        i=1;
        System.out.println("Get bit "+i+" of "+num+" = "+getBit(num,i));
        i=2;
        System.out.println("Get bit "+i+" of "+num+" = "+getBit(num,i));
        i=3;
        System.out.println("Get bit "+i+" of "+num+" = "+getBit(num,i));
        i=4;
        System.out.println("Get bit "+i+" of "+num+" = "+getBit(num,i));
        num=4;
        i=0;
        System.out.println("Set bit "+i+" of "+num+" = "+setBit(num,i));
        i=1;
        System.out.println("Set bit "+i+" of "+num+" = "+setBit(num,i));
        i=2;
        System.out.println("Set bit "+i+" of "+num+" = "+setBit(num,i));
        i=3;
        System.out.println("Set bit "+i+" of "+num+" = "+setBit(num,i));
        i=4;
        System.out.println("Set bit "+i+" of "+num+" = "+setBit(num,i));
        num=-1;  // 11111111
        i=0;
        // 2^0 = 1....000001 => 1111110 => 1111110 => 1000010
        // 1111111 clear bit 1 will be 1111101
        // 111111 clear bit 2 will be 11111011
        System.out.println("Clear bit "+i+" of "+num+" = "+clearBit(num,i));
        i=1;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearBit(num,i));
        i=2;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearBit(num,i));
        i=3;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearBit(num,i));
        i=4;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearBit(num,i));
        num=-1;  // 11111111
        i=0;
        // 2^0 = 1....000001 => 1111110 => 1111110 => 1000010
        // 1111111 clear bit 1 will be 1111101
        // 111111 clear bit 2 will be 11111011
        System.out.println("Clear bit "+i+" of "+num+" = "+clearMSBBits(num,i));
        i=1;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearMSBBits(num,i));
        i=2;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearMSBBits(num,i));
        i=3;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearMSBBits(num,i));
        i=4;
        System.out.println("Clear bit "+i+" of "+num+" = "+clearMSBBits(num,i));
        /**
         *
         */

    }
}
