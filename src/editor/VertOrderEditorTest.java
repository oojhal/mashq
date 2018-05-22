package editor;

import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;

/**
 * Created by ssiddiqu on 5/6/18.
 */
public class VertOrderEditorTest<T> {
    static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        public Node(T val) {
            this.val = val;
        }
        public Node<T> addLeft(Node<T> left) {
            this.left = left;
            return left;
        }
        public Node<T> addRight(Node<T> right) {
            this.right = right;
            return right;
        }
    }
    public static <T> void printVerticalOrder(Node<T> nd) {
        SortedMap<Integer,List<Node<T>>> vertOrder = new TreeMap<>();
        getVerticalOrder(nd,0, vertOrder);

    }
    public static <T> void getVerticalOrder(Node<T> nd, int level, SortedMap<Integer,List<Node<T>>> vertOrder) {
        if(nd !=null) {
            // find the list of node, if any at the current level
            List<Node<T>> nList = vertOrder.get(level);
            if(nList == null) {
                nList = new LinkedList<>();
                vertOrder.put(level, nList);
            }
            nList.add(nd);
            getVerticalOrder(nd.left, level-1, vertOrder);
            getVerticalOrder(nd.right, level+1, vertOrder);
        }
    }
    public static void vertOrderTest() {
        Node<Integer> root= new Node<Integer>(5);
        root.addLeft(new Node<Integer>(4)).addRight(new Node<Integer>(3));
    }
}