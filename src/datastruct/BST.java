package datastruct;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ssiddiqu on 2/11/18.
 */
public class BST<T> extends BinaryTree<T> {
    public BST(){

    }
    public BST(int nodeid, T val) {
        super(nodeid,val);
    }
    // create BST from sorted array
    public BST populateTree(List<T> treeArr) {
        BST tree=null;
        if((treeArr!=null)&&(treeArr.size()>0)) {
            tree = populateTree(treeArr, 0, treeArr.size()-1);

        }
        return tree;
    }

    public BST populateTree2(List<T> treeArr, int starti, int endi) {
        BST rnode=null;
        if(starti<=endi) {
            if(starti==endi) {
                rnode=  new BST(starti,treeArr.get(starti));
                rnode.leftChild=null;
                rnode.rightChild=null;
            }
            else {
                int midi= (endi+starti+1)/2;
                rnode= new BST(midi,treeArr.get(midi));
                rnode.leftChild = populateTree(treeArr,starti,midi-1);
                rnode.rightChild = populateTree(treeArr,midi+1,endi);
            }
        }
        return rnode;
    }
    public BST populateTree(List<T> treeArr, int starti, int endi) {
        BST rnode=null;
        if(starti<=endi) {
            // also takes care of start == endi test case since
            // midi = (start+end+1)/2 = [(2*start)+1]/2 = start
            //  leftChild = populateTree(treeArr, start, midi-1) =>  populateTree(treeArr, start, start-1)
            //  rightChild =
                int midi= (endi+starti+1)/2;
                rnode= new BST(midi,treeArr.get(midi));
                rnode.leftChild = populateTree(treeArr,starti,midi-1);
                rnode.rightChild = populateTree(treeArr,midi+1,endi);
        }
        return rnode;
    }

    public static void main(String[] args) {
        int[] data={1,2,3,4,5,6,7,8,9,10};
        List<Integer> ls = Arrays.stream(data).boxed().collect(Collectors.toList());
      //  Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        BST<Integer> bt = new BST<Integer>();
        bt = bt.populateTree(ls);
        BinaryTree.VisitedData<Integer> vsd = new VisitedData<Integer>();

        bt.visitInOrder((VisitedData vData, BinaryTree tr)-> {vData.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
        vData.visitVals.add(tr.val);}, vsd);
        System.out.println(vsd);
        System.out.println("*************************************");
        vsd = new VisitedData<Integer>();
        bt.bFS((VisitedData vData, BinaryTree tr)-> {
            vData.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
            vData.visitVals.add(tr.val);
        }, vsd);
        System.out.println(vsd);
        System.out.println("*************************************");
        vsd = new VisitedData<Integer>();
        BST<Integer> btnew = new BST<Integer>();
        bt.bFS((VisitedData vData, BinaryTree tr)-> {
            vData.visitStr.append(System.lineSeparator()+"visited node id= "+tr.nodeid+" with value= "+tr.val);
            btnew.val = new Integer(6);
            vData.visitVals.add(tr.val);
        }, vsd);
        System.out.println(btnew.val);
       // bt.bFS();
    }
}
