package datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class VisitedTree<T> {
    private static int TABSIZE=3;
    static class Node<T> {
        T value;
        int id;
        List<Node> children;
        boolean visited;
        public Node(T val, int id) {
            this.value= val;
            visited = false;
            this.id= id;
            children = new ArrayList<>();
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
    class NodeData<T> {
        Node<T> node;
        boolean visited;
        public NodeData(Node<T> nd){
            node=nd;
            visited=false;
        }
        void markVisited() {
            visited=true;
        }
        void clearVisited() {
            visited=false;
        }

    }
    Node<T> root;
    Map<Integer,NodeData<T>> nodesData = new HashMap<>();
    public VisitedTree() {
    }
    public void setRoot(Node<T> nd) {
        this.root = nd;
        addNodeData(nd);
    }
    private void addNodeData(Node<T> nd) {
        nodesData.put(nd.id, new NodeData<>(nd));
    }
    private void markVisited(Node<T> nd) {
        NodeData<T> ndt = nodesData.get(nd.id);
        if(ndt!=null) {
            ndt.markVisited();
        }
    }
    private void clearVisited(Node<T> nd) {
        NodeData<T> ndt = nodesData.get(nd.id);
        if(ndt!=null) {
            ndt.clearVisited();
        }
    }
    public void addNode(int parentId, Node<T> node) {
        NodeData<T> pndt = nodesData.get(parentId);
        if(pndt!=null) {
            // todo: error check to make sure that child does not already exist
            pndt.node.children.add(node);
            nodesData.put(node.id, new NodeData<>(node));
        }
    }
    public void addNode(int parentId, T val, int nodeId) {
        addNode(parentId, new Node<T>(val, nodeId));
    }
    public void addNodes(Object[][] nodesData) {
        for(Object[] ndata: nodesData) {
            addNode((Integer) ndata[0], (T) ndata[1], (Integer)ndata[2]);
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
        VisitedTree<Integer> tr = new VisitedTree<>();
        Node<Integer> rootNd = new Node<>(10,0);
        tr.setRoot(rootNd);
        Object[][] ndata = {{0,11,1},{0,12,2},{1,13,3} };
        tr.addNodes(ndata);
        System.out.println(tr);
    }
    public static void main(String[] args) {
        test();
    }
}
