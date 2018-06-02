package datastruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 5/24/18.
 */
public class BSTList<K extends Comparable<K>> {
    private static int TABSIZE = 3;
    public static class Node<K> {
        K val;
        public Node(K val) {
            this.val = val;
        }
    }
    private List<Node<K>> nodes;
    Comparator<K> cmp = Comparator.<K>naturalOrder();
    public BSTList(int sz) {
        nodes = new ArrayList<>(sz);
        for(int i=0; i< sz; i++) {
            nodes.add(null);
        }
    }

    public void addVal(K val) {
        Node<K> nd = new Node(val);
        addVal(1, nd);
    }
    private void addVal(int currNodeI, Node<K> ndI) {
        Node<K> nd = nodes.get(currNodeI);
        if(nd==null) {
            setNodeAt(currNodeI, ndI);
        }
        else if(cmp.compare(ndI.val, nodes.get(currNodeI).val) > 0) {
            // value to be inserted is larger than the value at current node
            addVal(getRightChildI(currNodeI), ndI);
        }
        else {
            addVal(getLeftChildI(currNodeI), ndI);
        }

    }
    private void setNodeAt(int indx, K val) {
        setNodeAt(indx, new Node(val));
    }
    private void setNodeAt(int indx, Node<K> nd) {
        while(nodes.size()<= indx) {
            nodes.add(null);
        }
        nodes.set(indx, nd);
    }
    private boolean withinBounds(int indx) {
        return (indx>=1) &&( indx< nodes.size());
    }
    public int compare(int nd1, int nd2) {
        int ret= 0;
        if(withinBounds(nd1)&&(withinBounds(nd2))) {
            ret = cmp.compare(nodes.get(nd1).val, nodes.get(nd2).val);
        }
        return ret;
    }
    public Node<K> getRoot() {
        return nodes.get(1);
    }
    private int getLeftChildI(int parent) {
        return 2*parent;
    }
    private int getRightChildI(int parent) {
        return (2*parent)+1;
    }
    private int getParent(int child) {
        return child/2;
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
            StringBuilder strb = new StringBuilder(getTab(tab)+nodes.get(ndInd));
            strb.append(System.lineSeparator());
            int lInd = getLeftChildI(ndInd);
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
    public static void lTest() {
        List<String> str = new ArrayList<>();
        str.add(null);
        str.add(null);
        str.set(1,"m");
        System.out.println(str);
        List<Integer> lis = Arrays.asList(5,4,8,2,6,10);
        BSTList<Integer> btl = new BSTList<>(lis.size()*3);
        for(Integer n: lis){
            btl.addVal(n);
        }
        System.out.println(lis);
    }

    public static void main(String[] args) {
        lTest();
    }
}
