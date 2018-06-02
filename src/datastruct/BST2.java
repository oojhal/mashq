package datastruct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssiddiqu on 4/7/18.
 * Handles:
 * http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/BST-delete2.html
 */
public class BST2<K extends Comparable<K>> {
    private static int TABSIZE = 3;

    public static class BSTNode<K> {
        K val;
        BSTNode<K> leftChild;
        BSTNode<K> rightChild;

        public BSTNode(K val) {
            this.val = val;
        }

        public void addLeftChild(BSTNode<K> lc) {
            leftChild = lc;
        }

        public void addRightChild(BSTNode<K> rc) {
            rightChild = rc;
        }

        public String toString() {
            return "value = " + val;
        }

    }

    BSTNode<K> root;

    public BST2() {
    }

    public void setRoot(BSTNode<K> root) {
        this.root = root;

    }

    public void insertVals(List<K> vals) {
        if ((vals != null) && (!vals.isEmpty())) {
            for (K val : vals) {
                insertVal(val);
            }
        }
    }

    public void insertVal(K val) {
        BSTNode<K> toI = new BSTNode<K>(val);
        if (root == null) {
            setRoot(toI);
        } else {
            insertVal(root, toI);
        }
    }

    private void insertVal(BSTNode<K> currNode, BSTNode<K> ndToInsert) {
        // if current node is > given val then the value must go as left child
        if (currNode.val.compareTo(ndToInsert.val) >= 0) {
            if (currNode.leftChild == null) {
                currNode.leftChild = ndToInsert;
            } else {
                insertVal(currNode.leftChild, ndToInsert);
            }
        } else {
            if (currNode.rightChild == null) {
                currNode.rightChild = ndToInsert;
            } else {
                insertVal(currNode.rightChild, ndToInsert);
            }

        }
    }

    public boolean deleteVal(K val) {

        return deleteVal(null, root, val);
    }

    public boolean deleteVal(BSTNode<K> parentNode, BSTNode<K> node, K val) {
        boolean ret = false;
        if (node != null) {
            // if found the value
            if (node.val.compareTo(val) == 0) {
                ret = true;
                deleteNode(parentNode, node);
            } else if (node.val.compareTo(val) >= 0) {
                // recurse down into left child if the current node is > value to be deleted
                deleteVal(node, node.leftChild, val);
            } else {
                // recurse down into right child if the current node is < value to be deleted
                deleteVal(node, node.rightChild, val);
            }
        }
        return ret;
    }

    /**
     * Deletes the specified node with the given parent node.
     * There are various cases:
     * (1) If the node to be deleted 'node' has left subtree (a single node or a tree),
     * then we find the maximum value node in the left subtree. Maximum value node
     * in the left subtree 'toMove' is the node in the left subtree that is the rightmost child i.e. its right child is null.
     * We copy the value of 'toMove' to 'node' and then make node's left child point to toMove's left child. That is because toMove does not have
     * right child and its left child is less than 'node'. 'node''s right child does not change. It works even when toMove's left child is null.
     * (2) If the node to be deleted 'node' has right subtree, then we find the minmum value node in the right subtree. Minimum value node
     * in the left subtree 'toMove' is the node in the right subtree that is the lefttmost child i.e. its left child is null.
     * We copy the value of 'toMove' to 'node' and then make node's right child point to toMove's right child. That is because toMove does not have
     * left child and its right child is more than the 'node'. 'node''s left child does not change. It works even when toMove's right child is null.
     *(3) Neither of the above is true i.e. the node to be deleted does not have a either right child or left child. Here we find out whether the node to
     * be deleted is the right or left child of parent. We then set corresponding child of node't parent to null.
     * This works even when the value to be deleted is at root
     * @param parent
     * @param node
     */
    public void deleteNode(BSTNode<K> parent, BSTNode<K> node) {
        // need to find either the max child in the left subtree
        // or min child in the right subtree
        // if parent of the node to be deleted is null, the node must be root

        if (node != null) {
            //left subtree is not null
            if (node.leftChild != null) {
                // keep track of the parent of the node to be moved
                BSTNode<K> toMoveP = node;
                BSTNode<K> toMove = node.leftChild;
                // at this point toMove!=null
                while (toMove.rightChild != null) {
                    toMoveP = toMove;
                    toMove = toMove.rightChild;
                }
                //toMove is the node to be moved
                //toMoveP is the parent of the node to be moved
                // copy the values of to Move to node
                node.val = toMove.val;
                // toMove right child is null, toMove may have a left child
                // toMove needs to be deleted. Left child of toMove if any becomes left subchild of parent
                toMoveP.leftChild = toMove.leftChild;
            } else if (node.rightChild != null) {
                // keep track of the parent of the node to be moved
                BSTNode<K> toMoveP = node;
                BSTNode<K> toMove = node.rightChild;
                // at this point toMove!=null
                while (toMove.leftChild != null) {
                    toMoveP = toMove;
                    toMove = toMove.leftChild;
                }
                //toMove is the node to be moved
                //toMoveP is the parent of the node to be moved
                // copy the values of to Move to node
                node.val = toMove.val;
                // toMove left child is null, toMove may have a right child
                // toMove needs to be deleted. Right child of toMove if any becomes right subchild of parent
                toMoveP.rightChild = toMove.leftChild;

            }
            // both the right and left child of the node to be deleted are null
            else {
                if(parent == null) {
                    setRoot(null);
                }
                else if (parent.leftChild==node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            }
        }
    }

    public String toString() {
        if(root == null)
            return "Tree:<null>";
        return "Tree:"+System.lineSeparator()+getString(root, 0, "");
    }

    private String getTab(int cnt) {
        String tStr = "";
        for (int i = 0; i < cnt * TABSIZE; i++) {
            tStr += " ";
        }
        return tStr;
    }

    public String getString(BSTNode<K> nd, int tab, String direc) {
        StringBuilder strb = new StringBuilder(getTab(tab) + direc + nd.toString());
        if (nd != null) {
            if (nd.leftChild != null) {
                strb.append(System.lineSeparator());
                strb.append(getString(nd.leftChild, tab + 1, "left: "));
            }
            if (nd.rightChild != null) {
                strb.append(System.lineSeparator());
                strb.append(getString(nd.rightChild, tab + 1, "right: "));
            }
        }
        return strb.toString();
    }

    public static void BST2Test() {
        BST2<Integer> bst2 = new BST2<>();
        int val=12;
        bst2.insertVal(val);
        System.out.println(bst2);
        bst2.deleteVal(val);
        System.out.println(bst2);
        List<Integer> vals = Arrays.asList(10, 5, 8, 7, 15, 3, 2, 25, 18, 29);
        val = 29;
        bst2.insertVals(vals);
        System.out.println(bst2);
        bst2.deleteVal(val);
        System.out.println("After deleting " + val);
        System.out.println(bst2);
        bst2.insertVal(val);
        System.out.println("After inserting " + val);
        System.out.println(bst2);
        val = 5;
        bst2.deleteVal(val);
        System.out.println("After deleting " + val);
        System.out.println(bst2);
        val = 25;
        bst2.deleteVal(val);
        System.out.println("After deleting " + val);
        System.out.println(bst2);
        val = 5;
        bst2.insertVal(val);
        System.out.println("After inserting " + val);
        System.out.println(bst2);
        val = 8;
        bst2.deleteVal(val);
        System.out.println("After deleting " + val);
        System.out.println(bst2);
        val=10;
        bst2.deleteVal(val);
        System.out.println("After deleting root " + val);
        System.out.println(bst2);
    }

    public static void main(String[] args) {
        BST2Test();
    }

}
