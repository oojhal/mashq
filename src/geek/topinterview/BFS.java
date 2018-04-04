package geek.topinterview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ssiddiqu on 3/17/18.
 */
public class BFS {
    public static class Node{
        int id;
        int val;
        boolean visited;
        List<Node> children;
        public Node(int id, int val) {
            this.id = id;
            this.val = val;
            visited= false;
            children = new LinkedList<>();
        }
        public String toString() {
            return "Node id= "+id+", value="+val;
        }
    }
    public static class Graph{
        Node[] allNodes;
        public Graph(int size) {
            allNodes = new Node[size];
        }
        public void addNode(int id, int val) {
            if(allNodes[id]==null) {
                Node nd = new Node(id, val);
                allNodes[id] = nd;
            }
        }
        public void addEdge(int fromNode, int toNode) {
            if((allNodes[fromNode]==null)||(allNodes[toNode]==null)) {
                throw new RuntimeException("The specified node does not exist");
            }
            allNodes[fromNode].children.add(allNodes[toNode]);
        }
        public void addNodesAndEdge(int fromNode, int toNode) {
            addNode(fromNode,fromNode);
            addNode(toNode,toNode);
            addEdge(fromNode,toNode);
        }
        public void BFS() {
           // boolean[] visited = new boolean[allNodes.length];
            for(Node nd: allNodes) {
                BFS(nd);
            }

        }
        public void BFS(Node nd) {
            Queue<Node> nodeQ = new LinkedList<>();
            // BFS is not recursive
            nodeQ.add(nd);

            while(!nodeQ.isEmpty()) {
                Node ndv = nodeQ.remove();
                if(!ndv.visited) {
                    System.out.println("Visited node "+ndv);
                }
                ndv.visited=true;
                for(Node child:ndv.children) {

                    nodeQ.add(child);
                }
            }

        }
        public static void testBFS() {
            Graph g = new Graph(4);
            g.addNode(0,100);
            g.addNode(1,200);
            g.addNode(2,300);
            g.addNode(3,400);
            g.addEdge(0, 1);
            g.addEdge(0, 2);
            g.addEdge(1, 2);
            g.addEdge(2, 0);
            g.addEdge(2, 3);
            g.addEdge(3, 3);

            System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

            g.BFS(g.allNodes[2]);
        }
        public static void main(String[] args) {
            testBFS();
        }
    }
}
