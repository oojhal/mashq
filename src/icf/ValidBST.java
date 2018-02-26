package icf;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ssiddiqu on 2/18/18.
 */
public class ValidBST{
    public static boolean isValidBST(ValidBST.BinaryTree<Integer> btree) {
        boolean ret = true;
        // affects the ret value only if btree is not null
        if (btree != null) {
            // affects the ret value only if leftChild is not null
            if (btree.leftChild != null) {
                //get the left child value
                ret = ((btree.val > btree.leftChild.val) && isValidBST(btree.leftChild));
            }
            // if left child is null it does not affect the ret value
            if (btree.rightChild != null) {
                ret = (ret && (btree.val < btree.rightChild.val) && isValidBST(btree.rightChild));
            }
        }
        return ret;
    }

    public static class BinaryTree<T> {
        T val;
        int nodeid;
        BinaryTree<T> leftChild;
        BinaryTree<T> rightChild;
        boolean visited;

        public BinaryTree() {

        }

        public BinaryTree(int nodeid, T val) {
            this.val = val;
            this.nodeid = nodeid;
            visited = false;
        }

        public BinaryTree populateTree(List<T> treeArr) {
            BinaryTree bt = null;
            if ((treeArr.size() > 0) && (treeArr.get(0) != null)) {
                bt = new BinaryTree(0, treeArr.get(0));
                populateNode(bt, 0, treeArr);
            } else {
                System.out.println("Can not construct tree from input arr");
            }
            return bt;
        }

        private void populateNode(BinaryTree<T> root, int ind, List<T> treeArr) {
            int lcind = (ind + 1) * 2 - 1;
            if ((lcind < treeArr.size()) && (treeArr.get(lcind) != null)) {
                BinaryTree<T> leftChild = new BinaryTree<>(lcind, treeArr.get(lcind));
                root.leftChild = leftChild;
                populateNode(leftChild, lcind, treeArr);
            }
            int rcind = (ind + 1) * 2;
            if ((rcind < treeArr.size()) && (treeArr.get(rcind) != null)) {
                BinaryTree<T> rightChild = new BinaryTree<T>(rcind, treeArr.get(rcind));
                root.rightChild = rightChild;
                populateNode(rightChild, rcind, treeArr);
            }

        }

        public void visitInOrder(Visitor<T> vstor) {
            visitInOrder(this, vstor);
        }

        public void visitInOrder(BinaryTree<T> node, Visitor<T> vstor) {
            if (node != null) {
                visitInOrder(node.leftChild, vstor);
                if (vstor != null) {
                    vstor.visit(node);
                } else
                    System.out.println(getNodeValStr(node));
                visitInOrder(node.rightChild, vstor);

            }
        }

        public T[] toOrderedVals() {
            return null;
        }

        private String getNodeValStr(BinaryTree root) {
            String ret = null;
            if (root != null) {
                ret = "visited node id= " + root.nodeid + " with value= " + root.val;
            }
            return ret;
        }

        public void bFS(Visitor<T> vstor) {
            bFS(this, vstor);
        }

        public <T> void bFS(ValidBST.BinaryTree<T> root, Visitor<T> vstor) {
            Queue<BinaryTree> bq = new LinkedList<BinaryTree>();
            bq.add(root);
            while (!bq.isEmpty()) {
                BinaryTree<T> currNode = bq.remove();
                if (vstor != null) {
                    vstor.visit(currNode);
                } else
                    System.out.println(getNodeValStr(currNode));

                currNode.visited = true;
                if ((currNode.leftChild != null) && (!bq.contains(currNode.leftChild)))
                    bq.add(currNode.leftChild);
                if ((currNode.rightChild != null) && (!bq.contains(currNode.rightChild)))
                    bq.add(currNode.rightChild);
            }
        }
        
    }
    public static class VisitedData<T> {
        StringBuilder visitStr;
        List<T> visitVals;
        public String toString() {
            return "Visited Nodes"+
                System.lineSeparator()+((visitStr ==null)? "none": visitStr.toString())+
                System.lineSeparator()+"node values"+visitVals;
        }
        public VisitedData(){
            visitStr=new StringBuilder();
            visitVals = new ArrayList<T>();
        }
    }
    public static <E extends Comparable> boolean isAscending(List<E> elems) {
        boolean isAsc = true;
        if((elems!=null)&&(elems.size()>1)) {
            E first = elems.get(0);
            for (int i = 1; i < elems.size(); i++) {
                //second element is less than first
                if(elems.get(i).compareTo(first)<0) {
                    return false;
                }
                first = elems.get(i);
            }
        }
        return isAsc;
    }
    @FunctionalInterface
    public interface Visitor<T> {
        public void visit(ValidBST.BinaryTree<T> bt);
    }
    public static void testIsAsc() {
        List<? extends Comparable> elems = Arrays.asList(1,2,3,4,5,6);
        System.out.println("Is ascending "+elems+"  "+isAscending(elems));
        elems = Arrays.asList(1,2,3,4,2,5,6);
        System.out.println("Is ascending "+elems+"  "+isAscending(elems));
        elems = Arrays.asList("a","b","c");
        System.out.println("Is ascending "+elems+"  "+isAscending(elems));
        elems = Arrays.asList("a","d","b","c");
        System.out.println("Is ascending "+elems+"  "+isAscending(elems));
    }
    public static void main(String[] args) {
        int[] data={1,1,2,3,4,5,6,7,8,9,10};
        // To boxed array
        Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        Integer[] ever = IntStream.of( data ).boxed().toArray( Integer[]::new );

// To boxed list
        List<Integer> you  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
        List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList() );
        testIsAsc();
        ValidBST.BinaryTree<Integer> bt = new ValidBST.BinaryTree<Integer>();
        bt = bt.populateTree(you);
        System.out.println(isValidBST(bt));
        List<Integer> bstdata = Arrays.asList(10,4,8,2,5,6,9);
        bt = new ValidBST.BinaryTree<Integer>();
        bt = bt.populateTree(bstdata);
        System.out.println(isValidBST(bt));
        bstdata = Arrays.asList(5,4,8,2,5,6,9);
        bt = new ValidBST.BinaryTree<Integer>();
        bt = bt.populateTree(bstdata);
        System.out.println(isValidBST(bt));
        VisitedData<Integer> vsd = new VisitedData<Integer>();
        bt.visitInOrder((ValidBST.BinaryTree<Integer> tr)-> {
            vsd.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
            vsd.visitVals.add(tr.val);
        });
        System.out.println(vsd);
        VisitedData<Integer> vsd2 = new VisitedData<Integer>();
        bt.bFS((ValidBST.BinaryTree<Integer> tr)-> {
            vsd2.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
            vsd2.visitVals.add(tr.val);
            });
        System.out.println(vsd2);
        System.out.println("*************************************");
    }
}
