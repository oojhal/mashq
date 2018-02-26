package datastruct;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ssiddiqu on 2/10/18.
 */
public class BinaryTree<T> {
    T val;
    int nodeid;
    BinaryTree<T> leftChild;
    BinaryTree<T> rightChild;
    boolean visited;
    public BinaryTree(){

    }
    public BinaryTree(int nodeid, T val) {
        this.val=val;
        this.nodeid=nodeid;
        visited =false;
    }
    public BinaryTree populateTree(List<T> treeArr) {
        BinaryTree bt= null;
        if((treeArr.size()>0)&&(treeArr.get(0)!=null)) {
            bt = new BinaryTree(0, treeArr.get(0));
            populateNode(bt,0,treeArr);
        }
        else {
            System.out.println("Can not construct tree from input arr");
        }
        return bt;
    }
    private void populateNode(BinaryTree root, int ind, List<T> treeArr) {
        int lcind= (ind+1)*2-1;
        if((lcind<treeArr.size())&&(treeArr.get(lcind)!=null)){
            BinaryTree leftChild = new BinaryTree(lcind, treeArr.get(lcind));
            root.leftChild= leftChild;
            populateNode(leftChild,lcind,treeArr);
        }
        int rcind= (ind+1)*2;
        if((rcind<treeArr.size())&&(treeArr.get(rcind)!=null)){
            BinaryTree rightChild = new BinaryTree(rcind,treeArr.get(rcind));
            root.rightChild= rightChild;
            populateNode(rightChild,rcind,treeArr);
        }

    }

    public void visitInOrder(Visitor vstor, VisitedData vsd) {
        visitInOrder(this, vstor, vsd);
    }

    public void visitInOrder(BinaryTree node, Visitor vstor, VisitedData vsd) {
        if(node!=null) {
            visitInOrder(node.leftChild, vstor,vsd);
            if(vstor!=null) {
                vstor.visit(vsd, node);
            }
            else
               System.out.println(getNodeValStr(node));
            visitInOrder(node.rightChild, vstor,vsd);

        }
    }
    public T[] toOrderedVals() {
       return null;
    }

    private static String getNodeValStr(BinaryTree root) {
        String ret= null;
        if(root!=null) {
            ret = "visited node id= "+root.nodeid+" with value= "+root.val;
        }
        return ret;
    }
    public void bFS(Visitor vstor, VisitedData vsd) {
        bFS(this, vstor, vsd);
    }
    public void bFS(BinaryTree root, Visitor vstor, VisitedData vsd) {
        Queue<BinaryTree> bq = new LinkedList<BinaryTree>();
        bq.add(root);
        while (!bq.isEmpty()) {
            BinaryTree currNode=bq.remove();
            if(vstor!=null) {
                vstor.visit(vsd, currNode);
            }
            else
                System.out.println(getNodeValStr(currNode));

            currNode.visited=true;
            if((currNode.leftChild!=null)&&(!bq.contains(currNode.leftChild)))
                bq.add(currNode.leftChild);
            if((currNode.rightChild!=null)&&(!bq.contains(currNode.rightChild)))
                bq.add(currNode.rightChild);
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

    @FunctionalInterface
    public interface Visitor<T> {
        public void visit(VisitedData vData, BinaryTree bt);
    }
    public static void main(String[] args) {
        int[] data={1,1,2,3,4,5,6,7,8,9,10};
        // To boxed array
        Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        Integer[] ever = IntStream.of( data ).boxed().toArray( Integer[]::new );

// To boxed list
        List<Integer> you  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
        List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList() );
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt = bt.populateTree(you);
        BinaryTree.VisitedData<Integer> vsd = new VisitedData<Integer>();
        bt.bFS((VisitedData vData, BinaryTree tr)-> {
            vData.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
            vData.visitVals.add(tr.val);
        }, vsd);
        System.out.println(vsd);
        System.out.println("*************************************");
    }
}
