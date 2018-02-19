package datastruct;

import java.util.*;

/**
 * Created by ssiddiqu on 1/27/18.
 */
public class GraphLinkedList {
    Node[] graph;
    public GraphLinkedList(int nodes) {
        graph = new Node[nodes];
        for(int i=0;i<nodes;i++) {
            addNode(i);
        }
    }
    public void BFS() {
        for(Node nd:graph) {
            BFS(nd);
        }
        clearVisited();
    }
    private void clearVisited() {
        for(Node nd: graph) {
            nd.visited=false;
            nd.visitedMap.clear();
        }

    }
    public void BFS(Node nd) {
        Queue<Node> dfsQ= new LinkedList<Node>();
        if(!nd.visited) {
            dfsQ.add(nd);
            System.out.println("Q before loop="+dfsQ);
            while(!dfsQ.isEmpty()) {
                System.out.println("Q inside loop="+dfsQ);
                Node pnd = dfsQ.remove();
                System.out.println("visited node"+pnd);
                pnd.visited = true;
                for(Node child:pnd.children) {
                    if((!child.visited)&&(!dfsQ.contains(child))) {
                        dfsQ.add(child);
                    }
                }
            }
        }
    }
    public boolean areLinked(int node1, int node2) {
        boolean reachable=false;
        Queue<Node> q1= new LinkedList<Node>();
        Queue<Node> q2= new LinkedList<Node>();
        Map<Node,String> col1= new HashMap<Node,String>();
        Map<Node,String> col2= new HashMap<Node,String>();
        q1.add(graph[node1]);
        q2.add(graph[node2]);
        while((!q1.isEmpty())&&(!q2.isEmpty())) {
            if(!q1.isEmpty()) {
                reachable = searchStep(q1, col1, col2, 1);
            }
            if(reachable) {
                break;
            }
            if(!q2.isEmpty()) {
                reachable = searchStep(q2, col2, col1, 2);
            }
            if(reachable) {
                break;
            }
        }
        clearVisited();
        return reachable;
    }

    public boolean searchStep(Queue<Node> q, Map<Node, String> myCol, Map<Node, String> otherCol, int pathId) {
        boolean reachable = false;
        Node nd = q.remove();
        if (!nd.getVisited(pathId)) {
            if (otherCol.get(nd) != null) {
                reachable = true;
            } else {
                nd.setVisited(pathId);
                myCol.put(nd, "p");
                for (Node child : nd.children) {
                    if (!q.contains(child)) {
                        q.add(child);
                    }
                }

            }

        }
        return reachable;
    }
    public void DFS() {
        for(Node nd:graph) {
            DFS(nd);
        }
        clearVisited();
    }

    /**
     * Stack 1 is root printed and method call for
     * @param nd
     */
    public void DFS(Node nd) {
        if((nd!=null)&&(!nd.visited)) {
            System.out.println("visited node"+nd);
            nd.visited=true;
            // visited node means it was printed but its children may not have been.
            for(Node child:nd.children){
              if(!child.visited) {
                  DFS(child);
              }
            }
        }
    }
    public void addNode(int nodeId) {
        Node nd = new Node(nodeId);
        graph[nodeId]=nd;
    }
    public void addEdge(int fromNdInd, int toNdInd) {
       if((fromNdInd<graph.length)||(toNdInd < graph.length)) {
           Node fromNode = graph[fromNdInd];
           Node toNode = graph[toNdInd];
           if((fromNode!=null)&&(toNode!=null)) {
               fromNode.addChildNode(toNode);
           }
       }
    }
    public void addEdges(int[][] edges) {
        for(int i=0;i<edges.length; i++) {
            for(int j=0;j<edges.length;j++) {
                if(edges[i][j]>0) {
                    addEdge(i,j);
                }
            }
        }
    }
    public String toString(boolean shortf) {
        StringBuffer ret = new StringBuffer();
        if((graph!=null)&&!(graph.length==0)) {
            int i=0;
            for(Node node:graph) {
                ret.append(node.toString(shortf));
                ret.append(System.lineSeparator());
            }
        }
        return ret.toString();
    }
    public String toString() {
        return toString(true);
    }
    public static class Node {
        public int id;
        public boolean visited=false;
        public List<Node> children;
        public Map<Integer,Boolean> visitedMap;
        public Node(int id){
            this.id = id;
            visited=false;
            visitedMap= new HashMap<>();
            children = new ArrayList<>();
        }
        public void setVisited(int pathId) {
            visitedMap.put(pathId,true);
        }
        public boolean getVisited(int pathId) {
            return (visitedMap.get(pathId)!=null);
        }
        public void addChildNode(Node nd) {
            if(!children.contains(nd)) {
                children.add(nd);
            }
        }
        public String toString() {
            return toString(true);
        }
        public String toString(boolean shortf) {
            StringBuilder strb = new StringBuilder();
            strb.append("node id="+id);
            strb.append(": visited="+visited);
            if(!(shortf)){
                strb.append(":  Connected to nodes:");
                for (Node child : children) {
                    strb.append(child.id);
                    strb.append(", ");
                }
            }
            return strb.toString();
        }
    }
    public static void main(String[] args) {
        int[][] edges =
            {
                {0,0,1,1,0,0},
                {0,0,1,1,0,0},
                {1,1,0,1,0,0},
                {1,1,1,0,0,0},
                {0,0,0,0,0,1},
                {0,0,0,0,1,0}
        };
        GraphLinkedList pls = new GraphLinkedList(edges.length);
        pls.addEdges(edges);
        System.out.println(pls.toString(false));
        System.out.println("********DFS*********************");
//        pls.DFS();
        pls.clearVisited();
        System.out.println("********BFS*********************");
        pls.BFS();
        pls.clearVisited();
        System.out.println(pls.areLinked(0,1));
        System.out.println(pls.areLinked(4,5));
        System.out.println(pls.areLinked(0,5));
    }
}
