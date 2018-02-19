package icf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ssiddiqu on 2/15/18.
 */
public class EncodeDecode {
    private static char SEP='|';
    private static char LENSEP=',';

    public static String encode(List<String> input) {
        // todo: check if null of empty return null
        StringBuilder strb = new StringBuilder();
        for(String str:input) {
            strb.append(str);
            strb.append(SEP);
        }
        return strb.toString();
    }

    public static List<String> decode(String input) {
        // to do: return null if input is null or empty
        int i=0;
        List<String> ret = new ArrayList<String>();
        while(i<input.length()) {
            int start = i;
            //until you hit the end of the string or separator
            while((i<input.length()&&(input.charAt(i++)!=SEP)));
            // reached either end of the string or the first sep character
            if(i-1>start) {
                ret.add(input.substring(start, i - 1));
                start = i;
            }
            //skip the sep character
            // get any strings consisting of | out of the way
            while(i<input.length()&&(input.charAt(i)==SEP)) {
                i++;
            }
            // reached the first non SEP character
            // have | at i-1
            if((i-1>start)&&(i<=input.length())) {
                ret.add(input.substring(start,i-1));
            }
            // at the first non SEP character that is beginning of new string
        }
        return ret;
    }
    /**
     * want to return 1,2,5 | fsdfsdfsf
     * @param input
     * @return
     */

    public static String encode2(List<String> input) {
        StringBuilder strs = new StringBuilder();
        StringBuilder lens = new StringBuilder();
        strs.append(input.get(0));
        lens.append(input.get(0).length());
        for(int i=1;i<input.size(); i++) {
            lens.append(LENSEP);
            lens.append(input.get(i).length());
            strs.append(input.get(i));
        }
        return lens.toString()+SEP+strs.toString();
    }
    public static List<String> decode2(String input) {
        String lenStr = input.substring(0,input.indexOf(SEP));
        //ignore the seperator
        String mStr = input.substring(input.indexOf(SEP)+1);
        List<String> ret = new ArrayList<String>();
        int[] lens = Arrays.stream(lenStr.split(String.valueOf(LENSEP))).mapToInt(Integer::parseInt).toArray();
        int currInd = 0;
        for(int len:lens) {
            if(len==0) {
                ret.add("");
            }
            else
            {
                ret.add(mStr.substring(currInd,currInd+len));
                currInd += len;
            }
        }
        return ret;

    }
    public static void main(String[] args) {
        // can not differenciate between "|","" and "||" as for both the returned string is:
        // "||, "" => "|"+"|"(sep)+""(empty)+"|"(sep) => "|||"
        // "||"  =>  "||"+"|"(sep) => "|||"
        //LOWERCASE.getIndex(s.charAt(i));
        for (int i = 0; i < 26; i++)
        {
            char upper = (char) ('A' + i);
            char lower = (char) ('a' + i);
            System.out.println("character "+upper+" has value "+(int)upper);
            System.out.println("character "+lower+" has value "+(int)lower);
        }
        int val = 'Z';
        System.out.println(val);
        System.out.println("z-a ="+((char)('z'+1)));
        List<String> inp = Arrays.asList("a","||","b","|||","");
        System.out.println(inp+" Encoded:"+encode(inp)+"  Decoded:"+decode(encode(inp)) );
        inp = Arrays.asList("a");
        System.out.println(inp+" Encoded:"+encode(inp)+"  Decoded:"+decode(encode(inp)) );
        inp = Arrays.asList("|");
        System.out.println(inp+" Encoded:"+encode(inp)+"  Decoded:"+decode(encode(inp)) );
        inp = Arrays.asList("a","||","b","|||","");
        System.out.println(inp+" Encoded:"+encode2(inp)+"  Decoded:"+decode2(encode2(inp)) );
        inp = Arrays.asList("a");
        System.out.println(inp+" Encoded:"+encode2(inp)+"  Decoded:"+decode2(encode2(inp)) );
        inp = Arrays.asList("|");
        System.out.println(inp+" Encoded:"+encode2(inp)+"  Decoded:"+decode2(encode2(inp)) );
    }
}
