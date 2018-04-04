package geek.topinterview;

/**
 * Created by ssiddiqu on 3/17/18.
 */
public class LowestCommonAncestor {
    static class Node {
        int id;
        Node left;
        Node right;
        public Node(int id) {
            this.id = id;
        }
        public String toString() {
            return String.valueOf(id);
        }
    }
    static class BinaryTree {
        Node root;
        public BinaryTree(Node root) {
            this.root= root;
        }
        public Node findLCA(int node1, int node2) {
            return LowestCommonAncestor.findLCA(root, node1, node2);
        }
    }

    /**
     * The function return lca if it exists.
     * If not, then it returns either node1 or node2 if found or null otherwise
     * @param nd
     * @param node1
     * @param node2
     * @return
     */
    public static Node findLCA(Node nd, int node1, int node2) {
        // this happens when leaf node is reached which indicates that neither
        // node1 nor node2 was found in the search
        if(nd==null)
             return null;
        // if the current node is the same as either of the two nodes then return it
        if((nd.id==node1)||(nd.id==node2)) {
            return nd;
        }
        // depth first search to find lca in left subtree. This will either return lca node under which both node1
        // node2 exist or one of the two nodes or null if none of the two nodes are found
        Node llca = findLCA(nd.left, node1, node2);
        // if the previous call does not find lca node and only returns one of the two nodes, then this call must
        // return the remaining node. if the previous call returns null i.e. it couldn't find either of the two
        // nodes, then this call must find both the node and return lca. If the previous calls does find lca then
        // this call must not find either of the two nodes and return null
        Node rlca = findLCA(nd.right, node1, node2);
        // if each of the above two call found one node each i.e. each of left and right subtree contain one of the two nodes
        if((llca!=null)&&(rlca!=null)) {
            return nd;
        }
        //return whatever the left or right subtree returned which is either just the found node or
        // the lca of the two nodes
        return (llca!=null) ? llca:rlca;
    }
    public static void testFindLCA() {
        {
            Node root = new Node(1);
            BinaryTree tree = new BinaryTree(root);
            tree.root = new Node(1);
            tree.root.left = new Node(2);
            tree.root.right = new Node(3);
            tree.root.left.left = new Node(4);
            tree.root.left.right = new Node(5);
            tree.root.right.left = new Node(6);
            tree.root.right.right = new Node(7);
            System.out.println("LCA(4, 5) = " +
                tree.findLCA(4, 5));
            System.out.println("LCA(4, 6) = " +
                tree.findLCA(4, 6));
            System.out.println("LCA(3, 4) = " +
                tree.findLCA(3, 4));
            System.out.println("LCA(2, 4) = " +
                tree.findLCA(2, 4));
        }
    }
    public static void main(String[] args) {
        testFindLCA();
    }
}
