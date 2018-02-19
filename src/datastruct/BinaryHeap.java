package datastruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class BinaryHeap<T> {
    Comparator<T>  cmp;
    List<Node<T>> nodes;
    public static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        public Node(T val) {
            this.val= val;
        }
    }
    public BinaryHeap(Comparator<T> cmp) {
        this.cmp = cmp;
        nodes = new ArrayList<>();
    }
    public void add(T v) {
        Node<T> ins = new Node<>(v);
        nodes.add(ins);
        int currInd = nodes.size()-1;
        bubbleUp(currInd);
    }
    private void bubbleUp(int nodeInd) {
        int parentInd = ((nodeInd+1)/2)-1;
        while((nodeInd>=0)&&(cmp.compare(nodes.get(nodeInd).val,nodes.get(parentInd).val)<=0)) {
            // swap the two elements
            Collections.swap(nodes, nodeInd, parentInd);
            nodeInd = parentInd;
            parentInd = ((nodeInd+1)/2)-1;
        }
    }
    private void bubbleDown(int nodeInd) {
        int lchildInd = (2*(nodeInd+1))-1;
        int rchildInd = 2*(nodeInd+1);
        int size = nodes.size();
        while(lchildInd<=size) {
            if(cmp.compare(nodes.get(nodeInd).val, nodes.get(lchildInd).val)<=0) {
                Collections.swap(nodes, nodeInd, lchildInd);
                nodeInd= lchildInd;
            }
            else if(cmp.compare(nodes.get(nodeInd).val, nodes.get(rchildInd).val)<=0) {
                Collections.swap(nodes, nodeInd, rchildInd);
                nodeInd= rchildInd;
            }
            lchildInd = (2*(nodeInd+1))-1;
            rchildInd = 2*(nodeInd+1);

        }
    }
    public String preOrder(Node<T> nd, StringBuffer sb) {
        return null;

    }
}
