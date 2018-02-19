package datastruct;

import java.util.*;

/**
 * Created by ssiddiqu on 2/16/18.
 * Input:
 dict[] = ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
 "HiTechWorld", "HiTechCity", "HiTechLab"]

 For pattern "HT",
 Output: ["HiTech", "HiTechWorld", "HiTechCity", "HiTechLab"]

 For pattern "H",
 Output: ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
 "HiTechWorld", "HiTechCity", "HiTechLab"]

 For pattern "HTC",
 Output: ["HiTechCity"]


 Input:
 dict[] = ["WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks"]

 For pattern "WTG",
 Output: ["WelcomeToGeeksForGeeks"]

 For pattern "GFG",
 Output: [GeeksForGeeks]

 For pattern "GG",
 Output: No match found
 First node in trie is empty
 */
public class HashTrie {
    /**
     * Each tri node corresponds to one character and stores all the matching word upto that node.
     * It contains bunch of child tri nodes that corresponds to the next character
     */
    public class TrieNode{
        Map<Character,TrieNode> children;
        char key;
        List<String> matchWords;
        public TrieNode() {

        }

    }
    TrieNode root;
    public HashTrie() {
        // root has only children
        // root does not belong to any character
        root = new TrieNode();
        root.children = new HashMap<>();
    }

    public void addString(String word) {
        addString(word,root.children);
    }
    public void addStrings(List<String> strs) {
        strs.forEach((str)-> addString(str));
    }

    /**
     * In the children of the current node, either find the node with given character or create
     * a new child with given character. Add the
     * @param word
     * @param children
     */
    public void addString(String word, Map<Character,TrieNode> children) {

        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(Character.isUpperCase(c)) {
                TrieNode curr = children.get(Character.valueOf(c));
                if(curr == null) {
                    // create a trie node for the given character and add it in the child collection
                    curr = new TrieNode();
                    curr.key = c;
                    children.put(c,curr);
                }
                List<String> words = curr.matchWords;
                if(curr.matchWords==null) {
                    // create a new list of matching words
                    curr.matchWords = new ArrayList<>();
                }
                curr.matchWords.add(word);
                if((curr.children == null)||(curr.children.size()==0)) {
                    curr.children=new HashMap<>();
                }
                children = curr.children;
            }
        }
    }
    public List<String> findMatches(String matchStr) {
        List<String> matches = null;
        if((root.children!=null)&&(!root.children.isEmpty())) {
            matches = findMatches(matchStr,root.children);
        }
        return matches;
    }
    public List<String> findMatches(String matchStr, Map<Character,TrieNode> children) {
        List<String> ret = null;
        for (int i = 0; i < matchStr.length(); i++) {
            if ((children == null) || (children.size() == 0)) {
                // still has characters left but ran out of nodes
                return null;
            }
            char ch = matchStr.charAt(i);
            // lowercase not allowed
            if (Character.isLowerCase(ch)) {
                return null;
            }
            TrieNode tr = children.get(ch);
            if(tr==null)
                return null;
            ret = tr.matchWords;
            // character matched, move to the next level
            children = tr.children;
        }
        return ret;
    }
    public static void testTrie(List<String> dict, List<String> testWords) {
        HashTrie ht = new HashTrie();
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
