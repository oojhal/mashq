package geek.topinterview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by ssiddiqu on 3/18/18.
 * A weighted directed graph is provided. The weight of a an edge represents distance. Need to find the shortest
 * weighted distance from a given node to all the other nodes.
 * Graph is represented as int[][]
 * Noes are numbered
 */
public class DijkstraShortestPath {
    /**
     * Finds the shortest path from src to all the other nodes in the graph.
     * The return array int[] represents the shortest distance of each node from source.
     * int[i] shortest distance from src to node i
     * @param graph
     * @param src
     * @return
     */
    /**
     *  done -> List of nodes for which shortest distance has been found. Initial value empty
     *  dist[] -> current minimum distance of node i from source node
     *  start by putting the distance of the source node as 0 in dist[]. All other values in dist[]
     *  will be Integer.MAX
     *  Start by choosing the node Nmin with minimum distance in dist[] so it starts with source node.
     *  Nmin is reachable from the source and it is reachable at minimum distance
     *  Chosen node Nmin to list of done nodes. Minimum distance from source node has already
     *  been found for it
     *  Now find all the other nodes that are reachable. Update their distances if less than current distance.
     *  So at all points, dist[] keeps track of the current minimum distance of a node reachable from source.
     *  The node with minimum value is the next closes to the source node
     *  This node can not be reachable from source through another shorter path as the shorter path must go through another node in
     *  the dist[] list. All these nodes have higher value ilel are at a larger distance from source already.
     *
     * @param graph
     * @param src
     * @return
     */
    public static int[] findShortestPaths(int[][] graph, int src) {
        //keep a list of nodes for which shortest path has already been found
        Set<Integer> done = new HashSet<>();
        // keep list of shortest paths.
        // paths[i] stores the shortest distance from src to node i
        int[] mindists = new int[graph.length];
        IntStream.range(0,mindists.length).forEach((idx)-> mindists[idx]=Integer.MAX_VALUE);
        Arrays.fill(mindists, Integer.MAX_VALUE);
        // path to the first node is 0
        mindists[src] = 0;
        // go through all the nodes in the graph
        // graph[i][j] represents the distance between node i and j
        // find all nodes that are shortest distance from current node

        while(done.size()!=graph.length) {
            int nextNode = findShortestDistNode(mindists, done);
            if(nextNode <0) {
                break;
            }
            // mark this node as done
            done.add(nextNode);
            // find the minimum edge of the next node
            updateNeighbourDists(graph, nextNode, mindists,done);

        }
        return mindists;
    }

    /**
     * Updates the current minimum distance of a node 'i' in mindists if
     * its current minimum distance in mindists[i] is greater than the current
     * minimum distance of currNode i.e. mindists[currNode] + distance between currNode
     * and node 'i' i.e. graph[currNode][i] if currNode and node 'i' are connected.
     * This indicates that the shortest path to node 'i' via currNode is shorter than the
     * previously calculated shortest path to node 'i'
     * @param graph
     * @param currNode
     * @param mindists
     */
    private static void updateNeighbourDists(int[][] graph, int currNode, int[] mindists, Set<Integer> done) {
        // row represents current node
        // column value represents distance to the node at column index
        for(int i=0; i< graph[currNode].length; i++) {
            // distance between source and currNode is mindists[currNode]
            // current distance between source and node i is mindists[i]
            // distance between currNode and i is graph[currNode][i]
            int distCurrAndT = graph[currNode][i];
            if(!done.contains(i) && distCurrAndT!=0 &&(mindists[i]> mindists[currNode]+distCurrAndT)) {
                // update the minimum distance from source to node i
                mindists[i] =mindists[currNode]+distCurrAndT;
            }
        }
    }

    /**
     * Finds the node with smallest value in minDists that is not there in done yet
     * @param minDists
     * @param done
     * @return
     */
    private static int findShortestDistNode(int[] minDists, Set<Integer> done) {
        int currMin = Integer.MAX_VALUE;
        int nodeIndx = -1;
        for(int i=0; i< minDists.length;i++) {
            // if node is not done its distance is less than current min
            if((!done.contains(i))&&(minDists[i]<= currMin)) {
                nodeIndx=i;
                currMin = minDists[i];
            }

        }
        return nodeIndx;
    }
    private static void testFindShortestPaths() {
                /* Let us create the example graph discussed above */
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        System.out.println(Arrays.toString(findShortestPaths(graph, 0)));
    }
    public static void main(String[] args) {
        testFindShortestPaths();
    }
}
