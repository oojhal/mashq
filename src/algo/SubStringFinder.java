package algo;

/**
 * Created by ssiddiqu on 1/30/18.
 */
public class SubStringFinder {
    public static void printSubStringMutations(String src, String mtch) {
        for(int i=0;i<src.length()-mtch.length();i++) {
            int mti= mtch.indexOf(src.charAt(i));
            if(mti>-1) {
                // match started
                printSubStringMutations(src,mtch.substring(0,mti)+mtch.substring(mti+1),String.valueOf(src.charAt(i)),i+1);
            }
        }
    }

    public static void printSubStringMutations(String src, String mtchR, String mtchD, int startIndex) {
        if(startIndex > (src.length() -1)) {
            return;
        }
        if(mtchR.isEmpty()) {
            System.out.println("Matched "+mtchD+" at end index "+startIndex);
            return;
        }
        Character srcC= src.charAt(startIndex);
        int ind = mtchR.indexOf(srcC);
        // character match
        if(ind>=0) {
            String mtchDN=mtchD+srcC;
            // remove the matched character from mtchR
            String mtchRN = mtchR.substring(0,ind)+mtchR.substring(ind+1);
            printSubStringMutations(src,mtchRN,mtchDN,++startIndex);
        }

    }
    public static void main(String[] args) {
        String src="headhhemaster";
        String mtch="headh";
        printSubStringMutations(src,mtch);
    }
}
