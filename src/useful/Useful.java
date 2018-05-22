package useful;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by ssiddiqu on 5/17/18.
 */
public class Useful {
    public static void collectionTest() {

        // character arithmatic
        char chr = 'a';
        System.out.println(chr+1);
        char newc = (char) (chr+1);
        System.out.println(newc);
        // create list from array
        List<Integer>  mList = Arrays.asList(8,9,4,5,10);
        System.out.println(mList);

        // create array from list
        Integer[] arr = mList.toArray(new Integer[mList.size()]);
        // Always create stream using Arrays.stream
        // toArray to get Array from stream
        int[] iArr = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();


        // create a character counter map
        /**
         * The generator function takes an integer, which is the size of the
         * desired array, and produces an array of the desired size.  This can be
         * concisely expressed with an array constructor reference:
         */
        Integer[] newArr = Arrays.stream(iArr).boxed().toArray(Integer[]::new);
        Integer[] newArr2 = Arrays.stream(iArr).boxed().toArray((sz) -> new Integer[sz]);
        // sort array
        printArr(newArr);

        Arrays.sort(newArr);
        printArr(newArr);

        // find maximum element of an array of values
        int[] val = new int[] {4,5,3,12,89,6,212};
        System.out.println();
        System.out.println("Max="+Arrays.stream(val).max());
        System.out.println("Max="+Arrays.stream(newArr).max(Comparator.<Integer>naturalOrder()));

        Map<Character,Integer> chrCounter = new HashMap<>();
        chrCounter.merge('a', 1, Integer::sum);
        chrCounter.merge('a', 1, Integer::sum);
        chrCounter.merge('a', 1, Integer::sum);
        chrCounter.merge('b', 1, Integer::sum);
        System.out.println(chrCounter);

        // List and Queue can both use LinkedList
        List<Integer> llist = new LinkedList<>();
        Queue<Integer> ql = new LinkedList<>();

        ql.add(1);
        ql.add(2);
        for(Integer i:ql) {
            System.out.println(i);
        }
        System.out.println(ql);

        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        stk.push(2);
        while(!stk.isEmpty()) {
            System.out.println(stk.pop());
        }
        System.out.println(stk);

        int[] vals = new int[6];
        Arrays.fill(vals,0);
        Arrays.stream(vals).forEach((n)-> System.out.print(n+","));
        Arrays.fill(vals,0,3,1);
        Arrays.stream(vals).forEach((n)-> System.out.print(n+","));

        Map<Character,Integer> ordMap = new TreeMap<>();
        ordMap.put('a',1);
        ordMap.put('c',3);
        ordMap.put('b',2);
        System.out.println(ordMap);

        Set<Integer> ordSet = new TreeSet<>();

        ordSet.addAll(Arrays.asList(5,3,12,5,1,0));
        System.out.println(ordSet);
        Set<Integer> lSet = new LinkedHashSet<>();
        lSet.addAll(Arrays.asList(5,3,12,5,1,0));
        System.out.println(lSet);
        Iterator<Integer> it = lSet.iterator();

        Map<Character,Integer> cMap = new LinkedHashMap<>();
        System.out.println("********");
        try {
            // the loop below will throw exception as the map is modified while it is being iterated over
            for (Map.Entry<Character, Integer> entry : ordMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
                ordMap.put((char) (entry.getKey().charValue() + 10), entry.getValue() + 1);
            }
        }
        catch(Exception e) {
            System.out.println("Exception while iterating over map "+e.getMessage());
        }
        System.out.println(ordMap);

        Iterator<Map.Entry<Character,Integer>> iter = ordMap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Character,Integer> entry = iter.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
            ordMap.put((char) (entry.getKey().charValue()+10) ,entry.getValue()+1);
        }
        System.out.println(ordMap);
    }
    static void getOccuringChar(String str)
    {
        // Create an array of size 256 i.e. ASCII_SIZE
        int MAX_CHAR = 256;
        int count[] = new int[MAX_CHAR];

        int len = str.length();

        // Initialize count array index
        for (int i = 0; i < len; i++)
            count[str.charAt(i)]++;

        // Create an array of given String size
        for(int j=0; j< MAX_CHAR; j++) {
            if(count[j]>0) {
                System.out.println("Count of char "+(char)j+" is "+count[j]);
            }
        }
    }
    private static void testCharCount() {
        getOccuringChar("a__&&&^^%%DDD00045<<:??ddfaaabb");
    }
    private static void printArr(Object[] arr) {
        System.out.println();
        Arrays.stream(arr).forEach((ob)-> System.out.print(ob+","));
    }
    public static void main(String[] args) {
        // collectionTest();
        testCharCount();
    }
}

