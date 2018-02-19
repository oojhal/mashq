package datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ssiddiqu on 2/18/18.
 */
public class ArrayTrie {
    private static int CSIZE='Z'-'A'+1;
    TrieNode[] children;
    public class TrieNode {
        char let;
        List<String> matchingWords;
        TrieNode[] children;

        public TrieNode()
        {
            matchingWords = new ArrayList<>();
            children = new TrieNode[CSIZE];
        }
    }
    public ArrayTrie(){
        children = new TrieNode[CSIZE];
    }
    public void addString(String word) {
        addString(word,children);
    }
    public void addString(String word, TrieNode[] children) {
        for(char let:word.toCharArray()) {
            // ignore all lower case letters
            if(Character.isUpperCase(let)) {
                // find the node for the letter
                // let - 'A' gives the index of the letter
                TrieNode mNode = children[let-'A'];
                if(mNode==null) {
                    // initializes matching words and children
                    mNode = new TrieNode();
                    mNode.let = let;
                    // add the new node as child
                    children[let-'A'] = mNode;
                }
                // if there are no matching words
                mNode.matchingWords.add(word);
                // move to the next level of trinode
                children = mNode.children;
            }
        }
    }
    public List<String> findMatches(String input) {
        return findMatches(input, this.children);
    }
    public List<String> findMatches(String input, TrieNode[] children) {
        List<String> ret=null;
        for(char let: input.toCharArray()) {
            if(Character.isLowerCase(let)) {
                return null;
            }
            TrieNode currN = children[let-'A'];
            // next matching letter not found
            if(currN==null) {
                return null;
            }
            // matching word list so far
            ret = currN.matchingWords;
            children = currN.children;
        }
        return ret;
    }
    public void addStrings(List<String> words) {
        words.forEach((word)-> this.addString(word));
    }
    public static void testTrie(List<String> dict, List<String> testWords) {
        ArrayTrie ht = new ArrayTrie();
        ht.addStrings(dict);
        testWords.forEach((mword) ->
            System.out.println("For input word "+mword+": Matches are "+ht.findMatches(mword)));

    }
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
            "HiTechWorld", "HiTechCity", "HiTechLab");
        List<String> mwords = Arrays.asList("HT","HTC","H");
        testTrie(words,mwords);
        words = Arrays.asList("WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks");
        mwords = Arrays.asList("WTG","GFG","GG");
        testTrie(words,mwords);
    }
}
