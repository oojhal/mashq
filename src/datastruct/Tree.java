package datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class Tree<T> {
    private static int TABSIZE=3;
    public static class Node<T> {
        T value;
        int id;
        List<Node<T>> children;
        public Node(T val, int id) {
            this.value= val;
            this.id= id;
            children = new ArrayList<>();
        }
        public List<Node<T>> getChildren() {
            return children;
        }
        public T getValue() {
            return value;
        }
        public boolean equals(Object other) {
            if(this==other){
                return true;
            }
            else if((other==null)||(other.getClass()!= this.getClass())) {
                return false;
            }
            return this.id==((Node) other).id;
        }
        public String toString() {
            return "id="+id+" value="+value;
        }
    }

    Node<T> root;
    Map<Integer,Node<T>> nodesData;
    public Tree() {
        nodesData = new HashMap<>();
    }
    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> nd) {
        this.root = nd;
        addNode(nd);
    }
    private void addNode(Node<T> nd) {
        nodesData.put(nd.id, nd);
    }
    public void addNode(int parentId, Node<T> node) {
        Node<T> pnd = nodesData.get(parentId);
        if(pnd!=null) {
            // todo: error check to make sure that child does not already exist
            pnd.children.add(node);
            nodesData.put(node.id, node);
        }
    }
    public void addNode(int parentId, int nodeId, T val) {
        addNode(parentId, new Node<T>(val, nodeId));
    }
    public void addNodes(Object[][] nodesData) {
        for(Object[] ndata: nodesData) {
            addNode((Integer) ndata[0], (Integer)ndata[1], (T) ndata[2]);
        }
    }
    public String toString() {
        return getString(root,0);
    }
    private String getTab(int cnt) {
        String tStr="";
        for(int i=0; i<cnt*TABSIZE; i++) {
            tStr+=" ";
        }
        return tStr;
    }
    public String getString(Node<T> nd, int tab) {
        StringBuilder strb = new StringBuilder(getTab(tab)+nd.toString());
        if(!nd.children.isEmpty()) {
            for(Node<T> chld: nd.children)  {
                strb.append(System.lineSeparator());
                strb.append(getString(chld,tab+1));
            }
        }
        return strb.toString();
    }
    public static void test() {
        Tree<Integer> tr = new Tree<>();
        Node<Integer> rootNd = new Node<>(10,0);
        tr.setRoot(rootNd);
        Object[][] ndata = {{0,1,11},{0,2,12},{1,3,13} };
        tr.addNodes(ndata);
        System.out.println(tr);
        Tree<String> tr2 = new Tree<>();
        Node<String> rootNd2 = new Node<>("adam",0);
        tr2.setRoot(rootNd2);
        Object[][] ndata2 = {{0,1,"adam ka pehla bacha"},{0,2,"adam ka doosra baccha"},{1,3,"adam kay pehlay bachay ka bachha"},{0,4,"adam ka teesra baccha"} };
        tr2.addNodes(ndata2);
        System.out.println(tr2);
    }
    public static void main(String[] args) {
        test();
    }
}
