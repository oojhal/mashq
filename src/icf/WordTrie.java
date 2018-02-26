package icf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssiddiqu on 2/23/18.
 */
public class WordTrie {
    public char chr = Character.MIN_VALUE;
    public boolean wend;
    public Map<Character,WordTrie> children;
    public WordTrie() {
        wend = false;
        children = new HashMap<>();
    }
    public void addWord(String word) {
        addWord(word, children);
    }
    public void addWord(String word, Map<Character,WordTrie> children) {
        WordTrie toadd = null;
        if((word!=null)&&(!word.isEmpty())) {
            Map<Character,WordTrie> currChil = children;
            for(char chr: word.toCharArray()) {
                // find the Trie with current character
                toadd = currChil.get(chr);
                if(toadd==null) {
                    toadd = new WordTrie();
                    toadd.chr= chr;
                    // add the trie node as child
                    currChil.put(chr,toadd);
                }
                // to add will have current character and be part of children
                currChil = toadd.children;
            }
            toadd.wend=true;
        }

    }
    public boolean wordExists(String word, boolean prefix){
        boolean exists = true;
        Map<Character,WordTrie> currChil = children;
        WordTrie currT= null;
        if((word!=null)&&(!word.isEmpty())) {
            for(char chr: word.toCharArray()) {
                currT = currChil.get(chr);
                if(currT == null)
                    return false;
                currChil = currT.children;
            }
            // all the characters ran out
        }
        // if it is full word match but the current node does not have word end flag
        if(!prefix &&(!currT.wend)) {
            exists = false;
        }
        return exists;
    }

    /**
     * Print all the words contained in the trie
     * @return
     */
    public String toString() {
        String ret="";
        List<String> words = new ArrayList<>();
        fillWords(this, "", words);
        for(String wrd: words) {
            ret = ret + wrd;
        }
        return ret;
    }

    private void fillWords(WordTrie tr, String currWord, List<String> words) {
        if(tr!=null) {
            currWord = currWord + tr.chr;
            final String word = currWord;
            if(tr.wend) {
                words.add(currWord);
            }
            if(tr.children!=null) {
                tr.children.forEach((chr, ctr) -> {
                    fillWords(ctr,word , words);
                });
            }
        }
    }
    public boolean wordExists(String word) {
        return wordExists(word, false);
    }
    public boolean prefixExists(String prefix) {
        return wordExists(prefix, true);
    }
    public static void testWordTrie() {
        WordTrie wt = new WordTrie();
        Arrays.asList("techtalk", "technical", "whatever","whateverelse","waterfall ").forEach((str)->wt.addWord(str));
        System.out.println("Trie = "+wt);
        Arrays.asList("tech","technical","wa").forEach((str) -> {
            if(wt.wordExists(str)) {
                System.out.println("Word "+ str +" exists in trie");
            }
            else {
                System.out.println("Word "+ str +" does not exist in trie");
            }
        });
        Arrays.asList("tech","technical","wa","nk").forEach((str) -> {
            if(wt.prefixExists(str)) {
                System.out.println("Prefix "+ str +" exists in trie");
            }
            else {
                System.out.println("Prefix "+ str +" does not exist in trie");
            }
        });

    }
    public static void main(String[] args) {

        testWordTrie();
    }
}
