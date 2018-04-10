package datastruct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 4/1/18.
 * Good resources to understand heap:
 * http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/
 * https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html
 * Creates a heap of values T. The heap is stored in a list
 * with the root node at index 1.
 */
public class ListHeap<T>{
    private static int TABSIZE=3;
    public static class Node<T>{
        T val;
        public Node(T val) {
            this.val= val;
        }
        public String toString() {
            return val.toString();
        }
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if ((obj== null) || (this.getClass() != obj.getClass())) {
                return false;
            } else {
                Node other = (Node) obj;
                return val.equals(other.val);
            }
        }
    }
    Comparator<T> cmp;
    // list of nodes in the heap. The list starts from
    // index 0
    private List<Node<T>> nodeList;
    public ListHeap(int sz, Comparator<T> cmp) {
        nodeList = new ArrayList<Node<T>>(sz+1);
        // null or dummy node at index 0
        // this is done to make it easy to calculate the parent
        // or child node indices
        nodeList.add(null);
        this.cmp = cmp;
    }

    /**
     * adds a new node with the specified value as the last node
     * and then percolates node up as needed to balance the heap
     * @param val
     */
    public void addVal(T val) {
        // add the value to end of the heap
        Node<T> nd = new Node<T>(val);
        nodeList.add(nd);
        // now move the inserted value up
        // parent of 1 and 2 is 0  (i+1)/2 -1
        // parent of 3 and 4 is 1  (i+1)/2 -1
        int currIndx = lastNodeIndex();
        percolateUp(currIndx);
    }
    public void addVals(List<T> vals) {
        for(T val:vals) {
            addVal(val);
        }
    }
    private int lastNodeIndex() {
        // the element at index 0 is null
        // the first element will be at index 1 and size() will be 2.
        // null at index 0 and first element at index 1.
        // so when there are n elements, the last element index will be n
        // but size will be n+1
        return nodeList.size()-1;
    }
    public int firstNodeIndex() {
        return 1;
    }
    public boolean withinBounds(int indx) {
        return ((indx<=lastNodeIndex())&&(indx>=firstNodeIndex()));
    }
    public int size() {
        return nodeList.size()-1;
    }
    private int getParentIndx(int curr) {
        return curr/2;
    }
    private int getLeftChildIndx(int curr) {
        return (curr*2);
    }

    /**
     * Compares the node values at specified indices
     * @param indx1
     * @param indx2
     * @return
     */
    public int compare(int indx1, int indx2) {
       return cmp.compare(nodeList.get(indx1).val, nodeList.get(indx2).val);
    }
    /**
     * deletes the root node of the heap
     */
    public void deleteRoot() {
        // delete root by swapping with the last node and percolate down
        deleteNodeAt(firstNodeIndex());
    }
    public T getRootValue() {
        T val = null;
        if(size()>0) {
            val = nodeList.get(firstNodeIndex()).val;
        }
        return val;
    }

    public void setValueAt(T val, int indx) {
        if(withinBounds(indx)) {
            nodeList.get(indx).val = val;
            reheapAt(indx);
        }
    }
    /**
     * deletes the node at index indx by setting the last node
     * at the index indx. Percolate up or down depending upon its
     * values relative to its parents or child
     * @param indx
     */
    public void deleteNodeAt(int indx) {
        Node lastNode = nodeList.remove(lastNodeIndex());
        if(size()>0) {
            nodeList.set(indx, lastNode);
            reheapAt(indx);
        }
    }
    private void reheapAt(int indx) {
        if(indx == firstNodeIndex()) {
            percolateDown(indx);
        }
        else {
            // compare with parent
            int parentIndx = getParentIndx(indx);
            // if currentNode is greater than parent then percolate up
            if(compare(indx, parentIndx)>0) {
                percolateUp(indx);
            }
            else {
                percolateDown(indx);
            }
        }
    }

    /**
     * delete node with specified value
     * @param val
     */
    public void deleteNodeWithVal(T val) {
        if(val!=null) {
            int valIndx=-1;
            // find the index of the node with the specified value
            for(int i=firstNodeIndex(); i<= lastNodeIndex(); i++) {
                if(val.equals(nodeList.get(i).val)) {
                    valIndx= i;
                    break;
                }
            }
            if(valIndx!= -1) {
                deleteNodeAt(valIndx);
            }
        }
    }
    /**
     * For naturalOrder comparator this will make sure that the current node
     * moves up if it is larger than parent
     * @param nodeIndx
     */
    public void percolateUp(int nodeIndx) {
        // if the node to heapify is still with the heap boundary
        if(withinBounds(nodeIndx)) {
            Node nd = nodeList.get(nodeIndx);
            int parentIndx =getParentIndx(nodeIndx);
            if((parentIndx>=firstNodeIndex())&&(compare(nodeIndx, parentIndx))>0) {
                swap(nodeIndx,parentIndx);
                nodeIndx= parentIndx;
                percolateUp(nodeIndx);
            }
        }
    }
    /**
     * For naturalOrder comparator this will make sure that the current node
     * moves down if it is smaller than the child. The bigger child will
     * take place of the current node
     * @param nodeIndx
     */
    public void percolateDown(int nodeIndx) {
        // if the node to heapify is still within the heap boundary
        if(withinBounds(nodeIndx)) {
            Node nd = nodeList.get(nodeIndx);
            int lchildIndx = getLeftChildIndx (nodeIndx);
            int maxChildIndx=-1;
            // find the maximum child index and value
            T maxChildVal= null;
            if(withinBounds(lchildIndx)) {
                maxChildIndx = lchildIndx;
                maxChildVal = nodeList.get(lchildIndx).val;
            }
            int rchildIndx = lchildIndx+1;
            if(withinBounds(rchildIndx)) {
                maxChildIndx = (compare(rchildIndx, maxChildIndx)>0) ? rchildIndx:maxChildIndx;
            }
            // maximum child is at maxChildIndx
            // if value at nodeIndx < maximum child is then it must percolate down
            if((withinBounds(maxChildIndx))&&(compare(nodeIndx, maxChildIndx)<0)) {
                swap(nodeIndx,maxChildIndx);
                nodeIndx= maxChildIndx;
                percolateDown(nodeIndx);
            }
        }
    }

    /**
     * Swaps the nodes at specified indices
     * @param indx1
     * @param indx2
     */
    private void swap(int indx1, int indx2) {
        if((withinBounds(indx1))&&(withinBounds(indx2))) {
            Node tmp = nodeList.get(indx1);
            nodeList.set(indx1, nodeList.get(indx2));
            nodeList.set(indx2, tmp);
        }
    }
    public String toString() {
        return getString(1,0);
    }
    private String getTab(int cnt) {
        String tStr="";
        for(int i=0; i<cnt*TABSIZE; i++) {
            tStr+=" ";
        }
        return tStr;
    }
    public String getString(int ndInd, int tab) {
        String ret = null;

        if(withinBounds(ndInd)) {
            StringBuilder strb = new StringBuilder(getTab(tab)+nodeList.get(ndInd));
            strb.append(System.lineSeparator());
            int lInd = getLeftChildIndx(ndInd);
            String lStr= getString(lInd,tab+1);
            if(lStr!=null) {
                strb.append(lStr);
            }
            String rStr= getString(lInd+1,tab+1);
            if(rStr!=null) {
                strb.append(rStr);
            }
            ret = strb.toString();
        }
        return ret;
    }
    /**
     * Get the value at root which is maximum or minimum
     * Delete root which reheaps and brings the maximum/ minimum value on top
     * @return
     */
    public List<T> heapSort() {
        List<T> sorted = new ArrayList<>();
        while(size()>0) {
            sorted.add(nodeList.get(firstNodeIndex()).val);
            deleteRoot();
        }
        return sorted;
    }
    public static void heapTest() {
        List<Integer> list = Arrays.asList(10,2,12,4,9,23);
        ListHeap<Integer> maxHeap = new ListHeap<Integer>(list.size(), Comparator.<Integer>naturalOrder());
        maxHeap.addVals(list);
        System.out.println(maxHeap);
        maxHeap.deleteNodeWithVal(12);
        System.out.println(maxHeap);
        System.out.println(maxHeap.heapSort());
        Comparator<Integer> minCmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        ListHeap<Integer> minHeap = new ListHeap<Integer>(list.size(), minCmp);
        minHeap.addVals(list);
        System.out.println(minHeap.heapSort());
        List<String> strs = Arrays.asList("a","b","c","d","e","f");
        ListHeap<String> maxStrHeap = new ListHeap<String>(strs.size(), Comparator.<String>naturalOrder());
        maxStrHeap.addVals(strs);
        System.out.println(maxStrHeap.heapSort());
    }
    public static void main(String[] args){
      heapTest();

    }
}
