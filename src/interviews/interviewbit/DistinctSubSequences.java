package interviews.interviewbit;

/**
 * Created by ssiddiqu on 6/4/18.
 */
public class DistinctSubSequences {
    public static int countDSSequences(String src, String trg)
    {
        if((src==null)||(src.isEmpty())) {
            return 0;
        }
        if((trg==null)||(trg.isEmpty())) {
            return 1;
        }
        int cCnt = 0;
        char chr = trg.charAt(0);
        int cindx = src.indexOf(chr);
        while(cindx!=-1) {
            //cCnt = cCnt + countDSSequences((src.substring(0,cindx)+((cindx< src.length())? src.substring(cindx+1):"")),trg.substring(1));
            cCnt = cCnt + countDSSequences(src.substring(cindx),trg.substring(1));
            cindx = src.indexOf(chr,cindx+1);
        }
        return cCnt;
    }
    public static void testCountDSequences() {
        String src1 = "AACCEE";
        String dest1 = "ACE";
        String src2 = "ADCEFECE";
        String dest2 = "ACE";
        String src = "ADCCEEF";
        String dest = "ADF";

        System.out.println(countDSSequences(src,dest));
    }
    public static void main(String[] args) {
        testCountDSequences();
    }
}
