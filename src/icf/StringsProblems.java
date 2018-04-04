package icf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static icf.DynamicProg.max;

/**
 * Created by ssiddiqu on 2/23/18.
 */
public class StringsProblems {
    public static final String SP = " ";

    public static void printSpiral(String[][] input) {
        if ((input == null) || (input.length == 0)) {
            return;
        }
        int rs = 0;
        int re = input.length - 1;
        int cs = 0;
        int ce = input[0].length - 1;
        int numElems = input.length * input[0].length;
        int visitedElems = 0;
        while (visitedElems < numElems) {
            // at top go all the way right
            for (int ci = cs; ci <= ce; ci++) {
                System.out.print(input[rs][ci] + SP);
                visitedElems++;
            }
            if (visitedElems >= numElems) {
                break;
            }
            // first row got done
            rs = rs + 1;
            // at last column move all the way down
            for (int ri = rs; ri <= re; ri++) {
                System.out.print(input[ri][ce] + SP);
                visitedElems++;
            }
            if (visitedElems >= numElems) {
                break;
            }
            // last column got done
            ce--;
            // at last row move from right to all the way left
            for (int cd = ce; cd >= cs; cd--) {
                System.out.print(input[re][cd] + SP);
                visitedElems++;
            }
            if (visitedElems >= numElems) {
                break;
            }
            // last row got done
            re--;
            // at first column move from bottom row to top
            for (int rd = re; rd >= rs; rd--) {
                System.out.print(input[rd][cs] + SP);
                visitedElems++;
            }
            if (visitedElems >= numElems) {
                break;
            }
            cs++;
        }
    }

    public static void testPrintSpiral() {
        String[][] input = new String[][]{
            {"my", "name", "now", "is"},
            {"is", "all", "right", "sarim"},
            {"this", "and", ",", "siddiqui"}
        };
        printSpiral(input);
    }

    public static List<String> getNeuronyms(String str) {
        if ((str == null) || (str.isEmpty())) {
            return null;
        }
        if (str.length() <= 2) {
            return Arrays.asList(str);
        }
        List<String> ret = new ArrayList<>();
        // get the substring that does not have first and last character
        String strp = str.substring(1, str.length() - 1);
        for (int i = 1; i <= strp.length(); i++) {
            getNeuronyms(strp, i).forEach((pword) -> {
                ret.add(str.charAt(0) + pword + str.charAt(str.length() - 1));
            });
        }
        return ret;
    }

    public static List<String> getNeuronyms(String str, int numSub) {
        // if nothing to substitute or if the number of characters to substitute is more than the current string length
        if ((numSub == 0) || (numSub > str.length())) {
            return null;
        }
        List<String> retl = new ArrayList<>();
        int ind = 0;
        // start at ind
        while (ind + numSub <= str.length()) {
            String ret = "";
            // first part of the string is from start till current ind -1 only when ind > 0
            // and there are more characters in the string than numSub
            ret = str.substring(0, ind);
            ret = ret + numSub;
            ret = ret + str.substring(ind + numSub);
            retl.add(ret);
            ind++;
        }
        return retl;
    }
    public class CharacterCounter {
        Map<Character,Integer> counter;
        public  CharacterCounter() {
            counter = new HashMap<>();
        }
        public void addCharacter(char chr) {
            counter.merge(chr,1, Integer::sum);
        }

    }

    static String findSubStringMy(String str, String pat) {
        if((str==null)||(str.isEmpty())||(pat == null) ||(pat.isEmpty()))
            return null;
        int strlen = str.length();
        int patlen = pat.length();
        if(patlen>strlen)
            return null;
        int[] strChrCntr = new int[256];
        int[] patChrCntr = new int[256];
        // iterate over pattern to build character count of pattern
        for(char ch:pat.toCharArray()) {
            patChrCntr[ch]++;
        }
        int matchStartIndx = 0;
        // keeps track of any current matches. After the match once, it is always currStrInd
        int lastMatchStart = -1;
        int lastMatchEnd = strlen+1;
        // iterate over all characters in string
        int charsMatched = 0;
        for(int currStrInd = 0; currStrInd < strlen; currStrInd++) {
            // increase the character count
            strChrCntr[str.charAt(currStrInd)]++;
            // see if this character matched any character in pattern
            // increase the count of matched characters
            // chrStr = str.charAt(currStrInd) gives the character at currStrInd
            // patChrCntr[chrStr] gives the count of that character in pattern string
            if(strChrCntr[str.charAt(currStrInd)]<=patChrCntr[str.charAt(currStrInd)] && patChrCntr[str.charAt(currStrInd)]>0) {
                charsMatched++;
            }
            // if all the characters have been matched in the pattern
            if(charsMatched == patlen) {
                // this is a potential match end window
                // move the matchStartIndx forward until the character count of the matched character is less than the character count
                while(strChrCntr[str.charAt(matchStartIndx)]>patChrCntr[str.charAt(matchStartIndx)] || patChrCntr[str.charAt(matchStartIndx)] ==0 ) {
                    if(strChrCntr[str.charAt(matchStartIndx)]>patChrCntr[str.charAt(matchStartIndx)]) {
                        strChrCntr[str.charAt(matchStartIndx)]--;
                    }
                    matchStartIndx++;
                }
                // check to see if current minimum window is smaller than the last match window
                // curr match window is matchStartIndx to currStrIndx
                // match window is from matchStartInd -> currIndx
                if((currStrInd-matchStartIndx) < (lastMatchEnd-lastMatchStart)) {
                    lastMatchStart = matchStartIndx;
                    lastMatchEnd = currStrInd;
                }
            }
        }
        if(lastMatchStart == -1) {
            return null;
        }
        else
        {
            return str.substring(lastMatchStart,lastMatchEnd+1);
        }
    }
    static final int no_of_chars = 256;


    /**
     * Function to find smallest window in source string that contains all string in a given pattern.
     * Input :  string =
     * pattern = "tist"
     * Output :  Minimum window is "t stri"
     * Explanation: "t stri" contains all the characters
     * of pattern.
     * <p>
     * Input :  string = "geeksforgeeks"
     * pattern = "ork"
     * Output :  Minimum window is "ksfor"
     * input : "tttrrrrrri"
     * pattern: "ti"
     * Algorithm is to store the occurrence count of each character in the pattern e.g. for the pattern tist
     * occurrence count of t = 2, i=1, s=1
     * Now scan the source string "this is a test string". For every character in the source, check to see if
     * it occurs in the pattern and if the number of times it has been encountered in source so far is still less
     * than number of times it occurs in pattern. So by the time the scan reaches the the second "t", in "this is a t", it would have
     * matches both the t's, i and s in the pattern "tist". Now try to minimize the match window by moving the match start index
     * forward. Match start index can be moved forward as long as the character at the index does not occur in pattern or if it does occur
     * in pattern but the occurrenece count in source string is more than the occurrence count in pattern.
     * Now compare the current minimum window after shrinking with the last minimum window
     *
     * @param str
     * @param pat
     * @return
     */
    static String findSubString(String str, String pat) {
        int len1 = str.length();
        int len2 = pat.length();

        // check if string's length is less than pattern's
        // length. If yes then no such window can exist
        if (len1 < len2) {
            System.out.println("No such window exists");
            return "";
        }
        // stores number of occurences of each character in pattern
        int hash_pat[] = new int[no_of_chars];
        // stores number of occurences of each character in target string
        int hash_str[] = new int[no_of_chars];

        // store occurrence count of characters of pattern
        for (int i = 0; i < len2; i++) {
            hash_pat[pat.charAt(i)]++;
        }

        int start = 0;
        // keeps track of start of the window
        int start_index = -1;
        // keeps track of minimum length
        int min_len = Integer.MAX_VALUE;

        // count of characters that have been matched
        int count = 0;
        // traverse the whole string
        for (int j = 0; j < len1; j++) {
            // count occurrence of characters of string
            hash_str[str.charAt(j)]++;

            // If string's char matches with pattern's char
            // then increment count
            // str.charAt(j) occurs in the pattern at least once
            // and the number of times it occurs is more than the
            // number of times it has occurred in string so far
            if (hash_pat[str.charAt(j)] != 0
                &&
                hash_str[str.charAt(j)] <= hash_pat[str.charAt(j)]) {
                count++;
            }

            // if all the characters are matched
            if (count == len2) {
                // Try to minimize the window i.e.,
                // check if any character in string is occurring more no. of times
                // than its occurrence  in pattern,
                // or if the character is not in pattern
                // if yes then remove it from starting and also remove
                // the useless characters.
                while (
                    // if the occurence count of character in str at start > occurence
                    // count of the the same character in pat
                    // all the matching characters are at index >= start
                    // any matching characters at start
                    hash_str[str.charAt(start)] > hash_pat[str.charAt(start)]
                        // if the character is not found in  pat
                        || hash_pat[str.charAt(start)] == 0) {

                    if (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)])
                    // decrease the count of the character in str
                    {
                        hash_str[str.charAt(start)]--;
                    }
                    start++;
                }

                // update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }

        // If no window found
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }

        // Return substring starting from start_index
        // and length min_len
        return str.substring(start_index, start_index + min_len);
    }

    /**
     * Given a string, find the length of the longest substring T that contains at most 2 distinct
     characters.
     For example, Given s = “eceba”,
     T is "ece" which its length is 3.
     * IF there are no such substrings (all same characters), then print nothing
     * If there multiple such strings, then print any one
     * @param source
     * @return
     */
    static String findStringWithTwoDistinct(String source) {
        if((source== null)||(source.isEmpty()))
            return null;
        if(source.length()==1)
            return source;
        int start = -1;
        int end = source.length()+1;
        char first = source.charAt(0);
        int indx = 1;
        while(source.charAt(++indx)!=first);
        end = indx;
        char second = source.charAt(indx);
        // found two charact
/**        for()
        for(int i=1; i< source.length(); i++) {
            char second =  source.charAt(i);
            if(first!=second) {

            }
        }
 **/
 return null;
    }
    // Driver Method
    public static void testFindSubstring() {
        String str = "this is a test string";
        String pat = "tist";

        System.out.println(pat + " occurs in smallest window :  " +
            findSubString(str, pat) + " in " + str);
        str = "tttrrrrrri";
        pat = "ti";
        System.out.println(pat + " occurs in smallest window :  " +
            findSubString(str, pat) + " in " + str);
    }
    // Driver Method
    public static void testMyFindSubstring() {
        String str = "this is a test string";
        String pat = "tist";

        System.out.println(pat + " occurs in smallest window :  " +
            findSubStringMy(str, pat) + " in " + str);
        str = "tttrrrrrri";
        pat = "ti";
        System.out.println(pat + " occurs in smallest window :  " +
            findSubStringMy(str, pat) + " in " + str);
        str = "tttrrrrrri";
        pat = "tiq";
        System.out.println(pat + " occurs in smallest window :  " +
            findSubStringMy(str, pat) + " in " + str);
    }
    public static void testAlpha() {
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println("character c " + c + " = int " + (int) c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.println("character c " + c + " = int " + (int) c);
        }
        for (int i = 0; i <= 256; i++) {
            System.out.println("int " + i + " = character " + (char) i);
        }
    }

    public static void testGetNeuronyms() {
        String input = "nailed";
        System.out.println(getNeuronyms(input));
    }
    public static boolean isInterleaved(String str1, String str2, String strI) {
        int len1= str1.length();
        int len2= str2.length();
        int lenI = strI.length();
        int[] indx1 = new int[len1];
        int[] indx2= new int[len2];
        int maxIndx = max(len1, len2);
        for(int i=0; i< maxIndx;i++) {
            if(i>= strI.length()) {
                break;
            }
            if(i<len1) {
                // check for str1
                int charIndx = firstIndexOfChar(str1.charAt(i),strI,(i==0)? 0:indx1[i-1]);
                if(charIndx>=lenI) {
                    return false;
                }
                indx1[i] = charIndx;
            }
            if(i<len2) {
                // check for str1
                int charIndx = firstIndexOfChar(str2.charAt(i),strI,(i==0)? 0:indx2[i-1]);
                if(charIndx>=lenI) {
                    return false;
                }
                indx2[i] = charIndx;
            }
        }
        return ((indx1[len1-1]!=0)&&(indx2[len2-1]!=0));
    }
    public static void testisInterleaved() {
        String str1= "XYA";
        String str2="AXC";
        String strI="AXBYCA";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        str1= "XYM";
        str2="AXC";
        strI="AXBYCA";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        str1= "XXY";
        str2="XXZ";
        strI="XXZXXXY";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        str1= "XY";
        str2="WZ";
        strI="WZXY";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        str1= "XY";
        str2="X";
        strI="XXY";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        str1 = "aabcc";
        str2 = "dbbca";
        strI = "aadbbbaccc";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
        strI ="aadbbcbcac";
        System.out.println("String "+strI+" contains both "+str1+" and "+str2+" = "+isInterleaved(str1,str2, strI));
    }
    private static int firstIndexOfChar(char chr, String strI, int start) {
        if(start>= strI.length())
            return start;
        int indx=start;
        while((indx<strI.length())&&(strI.charAt(indx)!=chr)) {
            indx= indx+1;
        }
        return indx;
    }
    public static void main(String[] args) {
//        testGetNeuronyms();
//        testPrintSpiral();
//        testAlpha();
//        testFindSubstring();
//        testMyFindSubstring();
        testisInterleaved();
    }
}
