package geek.topinterview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This function determines if there is a cycle in the graph
 * Can keep track of the visited nodes so far in a boolean array
 * Can keep track of the parents so far in parents boolean array
 * For DFS, a node is visited and all of its children are visited
 * When the control comes back ot the current method to a given node,
 * its whole subtree has already been visited. So parents boolean array
 * must be set to false
 * Created by ssiddiqu on 3/20/18.
 */
public class GraphCycle {
    public static class Node{
        int id;
        int val;
        List<Node> adj;
        public Node(int id, int val) {
            this.id= id;
            this.val = val;
            adj = new LinkedList<>();
        }
        public void addNode(Node connected) {
            adj.add(connected);
        }
        public void removeNode(Node connected) {
            adj.remove(connected);
        }
    }
    public static class Graph {
        // array of nodes
        Node[] allnodes;
        // creates a graph of the specified size
        public Graph(int size) {
            allnodes = new Node[size];
        }
        public void addNode(int id, int val) {
            if(allnodes[id]!=null) {
                throw new RuntimeException("Node "+id+" already exists");
            }
            allnodes[id] = new Node(id,val);
        }

        public void addEdge(int from, int to) {
            if((allnodes[from]==null)||(allnodes[to]==null)) {
                throw new RuntimeException(" specified nodes do not exist");
            }
            allnodes[from].addNode(allnodes[to]);
        }
        public void removeEdge(int from, int to) {
            if((allnodes[from]==null)||(allnodes[to]==null)) {
                throw new RuntimeException(" specified nodes do not exist");
            }
            allnodes[from].removeNode(allnodes[to]);
        }
    }
    public static boolean hasCycle(Graph graph) {
        boolean[] visited = new boolean[graph.allnodes.length];
        Arrays.fill(visited, false);
        boolean[] parents = new boolean[graph.allnodes.length];
        Arrays.fill(parents, false);
        for(Node nd: graph.allnodes) {
            if(!visited[nd.id]) {
                if(hasCycle(nd, visited, parents)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param nd
     * @param visited
     * @param parents maintains the list of parents of the current node nd
     * @return
     */
    private static boolean hasCycle(Node nd, boolean[] visited, boolean[] parents) {
        // if there is an edge from nd to another node and that node
        // is in the parents list, then there is a cycle
        boolean cycle= false;;
        for(Node child: nd.adj) {
           // if the
            if(!visited[child.id]) {
                // there is an edge from nd to child
                // if the child is one of the parents
                if(parents[child.id]) {
                    cycle= true;
                }
                else {
                    parents[nd.id] = true;
                    cycle = hasCycle(child,visited, parents);
                }
                if(cycle) {
                    break;
                }
            }
        }
        // mark the node visited as the whole subtree has been visited
        visited[nd.id]=true;
        // mark current node false in parents array as nd is not a parent
        // of any subsequently visited node. The whole subtree of nd has
        // already been visited
        parents[nd.id] = false;
        return cycle;
    }
    public static void testHasCycle() {
        Graph g = new Graph(4);
        for(int i=0;i<4;i++){
            g.addNode(i,i);
        }
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("Graph has cycle "+hasCycle(g));
        g.removeEdge(2,0);
        System.out.println("Graph has cycle "+hasCycle(g));
        g.removeEdge(3,3);
        System.out.println("Graph has cycle "+hasCycle(g));

    }
    public static void main(String[] args){
        testHasCycle();
    }
}
