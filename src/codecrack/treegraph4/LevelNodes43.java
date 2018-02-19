package codecrack.treegraph4;

import datastruct.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ssiddiqu on 2/10/18.
 */
public class LevelNodes43 {
    public static List<LinkedList<BinaryTree>> createList(BinaryTree root) {
        List<LinkedList<BinaryTree>> retList = new ArrayList<>();
        return null;

    }
    public void addNode(BinaryTree currNode, int level, List<LinkedList<BinaryTree>> retList) {
        if(currNode!=null) {
            LinkedList<BinaryTree> nodeList= retList.get(level);
        }
    }
}
