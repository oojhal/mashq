package geek.topinterview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ssiddiqu on 4/20/18.
 */
public class BTreeVertical<T> {
    static class Node<T> {
        Node<T> left;
        Node<T> right;
        T val;
        Node(T val) {
            this.val = val;
        }
        Node<T> addLeft(T val) {
            Node<T> left = new Node<>(val);
            this.left = left;
            return left;
        }
        Node<T> addRight(T val) {
            Node<T> right = new Node<>(val);
            this.right = right;
            return right;
        }
    }
    Node<T> root;
    BTreeVertical(Node<T> root) {
        this.root=root;
    }
    public static <T> void  printVertical(BTreeVertical<T> tree) {
        SortedMap<Integer,List<T>> vertOrder = new TreeMap<>();
        getVerticalOrder(tree.root, 1, vertOrder);
        for(Map.Entry<Integer, List<T>> entr: vertOrder.entrySet()) {
            System.out.println(entr.getValue());
        }
    }
    public static <T> void  getVerticalOrder(Node<T> nd, int level, SortedMap<Integer,List<T>> vertOrder) {
        if(nd!=null) {
            List<T> orderL = vertOrder.get(level);
            if(orderL == null ) {
                orderL = new ArrayList<T>();
                vertOrder.put(level, orderL);
            }
            orderL.add(nd.val);
            getVerticalOrder(nd.left, level-1, vertOrder);
            getVerticalOrder(nd.right, level+1, vertOrder);
        }
    }
    public static void printVerticalTest() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> left = root.addLeft(2);
        left.addLeft(4);
        left.addRight(5);
        Node<Integer> right = root.addRight(3);
        left = right.addLeft(6);
        right = right.addRight(7);
        left.addRight(8);
        right.addRight(9);
        BTreeVertical<Integer> bt = new BTreeVertical<Integer>(root);
        printVertical(bt);
    }
    public static void main(String[] args) {
        printVerticalTest();
    }
}
