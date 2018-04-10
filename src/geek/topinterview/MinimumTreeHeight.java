package geek.topinterview;

import datastruct.Tree;

/**
 * Created by ssiddiqu on 4/5/18.
 */
public class MinimumTreeHeight {
    public static <T> int minTreeHeight(Tree<T> tree) {
        return minDistToLeaf(tree.getRoot());
    }
    private static <T> int minDistToLeaf(Tree.Node<T> nd) {
        // initialize as if current node is leaf
        // if there is only 1 node then height is 1
        int minDist = 1;
        if((nd.getChildren()!=null)&&(!nd.getChildren().isEmpty())) {
            // there is at least one child
            int minC = Integer.MAX_VALUE;
            for(Tree.Node<T> child: nd.getChildren()) {
                int currMin = minDistToLeaf(child);
                if(currMin< minC) {
                    minC= currMin;
                }
            }
            minDist += minC;
        }
        return minDist;
    }
    public static void testMinTreeHeight() {
        Tree<Integer> tr = new Tree<>();
        Tree.Node<Integer> rootNd = new Tree.Node<>(10,0);
        tr.setRoot(rootNd);
        Object[][] ndata = {{0,1,11},{0,2,12},{1,3,13},{2,4,14} };
        tr.addNodes(ndata);
        System.out.println(minTreeHeight(tr));
    }
    public static void main(String[] args) {
        testMinTreeHeight();
    }
}
