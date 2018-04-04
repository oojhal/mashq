package datastruct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 4/1/18.
 * Creates a heap of values T. The heap is stored in a list
 * with the root node at index 1.
 */
public class ListHeap<T>{
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
            } else if (this.getClass() != obj.getClass()) {
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
     * deletes the node at index indx by making setting the last node
     * at the index indx. Now this node either needs to be percolated
     * up or down depending upon its values relative to its parents or child
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
            if((parentIndx>=firstNodeIndex())&&(compare(indx, parentIndx)>0)) {
                percolateUp(indx);
            }
            else {
                percolateDown(indx);
            }
        }
    }
    public void deleteNodeWithVal(T val) {
        if(val!=null) {
            int valIndx=-1;
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
            // int leftChildI =
        }
    }
    /**
     * For naturalOrder comparator this will make sure that the current node
     * moves down if it is smaller than the child. The bigger child will
     * take place of the current node
     * @param nodeIndx
     */
    public void percolateDown(int nodeIndx) {
        // if the node to heapify is still with the heap boundary
        if(withinBounds(nodeIndx)) {
            Node nd = nodeList.get(nodeIndx);
            int lchildIndx = getLeftChildIndx (nodeIndx);
            int maxChildIndx=-1;
            // find the maximum child index and value
            T maxChildVal= null;
            if(lchildIndx<=lastNodeIndex()) {
                maxChildIndx = lchildIndx;
                maxChildVal = nodeList.get(lchildIndx).val;
            }
            int rchildIndx = lchildIndx+1;
            if(rchildIndx <= lastNodeIndex()) {
                maxChildIndx = (compare(rchildIndx, maxChildIndx)>0) ? rchildIndx:maxChildIndx;
            }
            // maximum child is at maxIndx
            // if value at nodeIndx < maximum child is then it must percolate down
            if((withinBounds(maxChildIndx))&&(compare(nodeIndx,maxChildIndx)<0)) {
                swap(nodeIndx,maxChildIndx);
                nodeIndx= maxChildIndx;
                percolateDown(nodeIndx);
            }
        }
    }
    private void swap(int indx1, int indx2) {
        if((withinBounds(indx1))&&(withinBounds(indx2))) {
            Node tmp = nodeList.get(indx1);
            nodeList.set(indx1, nodeList.get(indx2));
            nodeList.set(indx2, tmp);
        }
    }
    public String toString() {
        return nodeList.toString();
    }
    public List<T> heapSort() {
        List<T> sorted = new ArrayList<>();
        while(size()>0) {
            sorted.add(nodeList.get(firstNodeIndex()).val);
            deleteRoot();
        }
        return sorted;
    }
    public static void heapTest() {
        // int[] list = {10,2,12,4,9,23};
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
