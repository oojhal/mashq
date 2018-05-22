package geek.topinterview;

import datastruct.Tree;

import java.util.List;

/**
 * https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
 * A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes.
 * Conversely, there is no node in a full binary tree, which has one child node.
 * Created by ssiddiqu on 4/7/18.
 */
public class CheckFullBtree {
    public static <T> boolean isFull(Tree<T> btree) {
        boolean ret = true;
        if(btree!=null){
            ret= isFull(btree.getRoot());
        }
        return ret;
    }
    public static <T> boolean isFull(Tree.Node<T> node) {
        boolean ret = true;
        if(node!=null) {
            List<Tree.Node<T>> children = node.getChildren();
            // if there are no children, it is a full tree
            if((children!=null)&&(!children.isEmpty())) {
                if(children.size()!=2) {
                    // if the number of children is not 2 then it is not a full tree
                    ret=false;
                }
                else {
                    // if there are 2 children then each of them have to be full tree
                    ret= isFull(children.get(0)) &&(isFull(children.get(1)));
                }
            }
        }
        return ret;
    }
    public static void testIsFull() {
        Tree<String> tr2 = new Tree<>();
        Tree.Node<String> rootNd2 = new Tree.Node<>("adam",0);
        tr2.setRoot(rootNd2);
        Object[][] ndata2 = {{0,1,"adam ka pehla bacha"},{0,2,"adam ka doosra baccha"},{1,3,"adam kay pehlay bachay ka bachha"},{0,4,"adam ka teesra baccha"} };
        tr2.addNodes(ndata2);
        System.out.println(tr2);
        System.out.println(isFull(tr2));
    }
    public static void main(String[] args) {
        testIsFull();
    }
}
