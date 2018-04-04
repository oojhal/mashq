package geek.topinterview;

import java.util.Arrays;

/**
 * Created by ssiddiqu on 3/20/18.
 */
public class FloydShortestPaths {
    public static int[][] findShortestPaths(int[][] graph){
        int[][] sPaths = new int[graph.length][];
        for(int i=0; i<graph.length;i++) {
            sPaths[i] = new int[graph[i].length];
            for(int j=0;j<graph[i].length;j++) {
                sPaths[i][j] = (graph[i][j]==0) ? Integer.MAX_VALUE:graph[i][j];
            }
        }

        // go through each node
        for(int nodeI= 0; nodeI< graph.length; nodeI++) {
            // find all the nodes that has edge to nodeI
            // graph[otherNode][nodeI]
            for(int otherNode=0; otherNode<graph.length; otherNode++){
                // there exists an edge from otherNode -> nodeI
                if(sPaths[otherNode][nodeI]!=Integer.MAX_VALUE) {
                    // otherNode -> nodeI exists
                    // find all the shortest distances from nodeI -> ooNode
                    for(int ooNode=0; ooNode< graph[nodeI].length; ooNode++) {
                        // if there exists a path between nodeI and ooNode
                        if(sPaths[nodeI][ooNode]!=Integer.MAX_VALUE) {
                            // from otherNode -> nodeI -> ooNode, compare it with the
                            // current shortest path from otherNode-> ooNode
                            // new path from nodeI to ooNode via
                            int newPath = sPaths[otherNode][nodeI] + sPaths[nodeI][ooNode];
                            if(newPath< sPaths[otherNode][ooNode]) {
                                sPaths[otherNode][ooNode] = newPath;
                            }
                        }
                    }
                }
            }
        }
        return sPaths;

    }
    private static void testFindShortestPaths() {
                /* Let us create the example graph discussed above */
        int graph[][] = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        int[][] paths = findShortestPaths(graph);
        Arrays.stream(paths).forEach((arr)-> System.out.println(Arrays.toString(arr)));
        int INF=0;
        graph = new int[][] {
            {0,   5,  0, 10},
            {INF, 0,   3, INF},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0}
        };
        paths = findShortestPaths(graph);
        Arrays.stream(paths).forEach((arr)-> System.out.println(Arrays.toString(arr)));
    }
    public static void main(String[] args) {
        testFindShortestPaths();
    }
}
