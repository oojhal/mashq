package hacker;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;
/**
 * Created by ssiddiqu on 3/30/18.
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem
 */
public class MinDistances {
    public static void printDistances(int[][] graph, int startNode) {
        int[] distances = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> toV = new LinkedList<Integer>();
        toV.add(startNode);
        while(!toV.isEmpty()) {
            int parentN = toV.remove();
            // visited[parentN] = true;
            // distance from startNode-> nodeV is distances[nodeV]
            // find all the children of current node
            for(int childNode=0; childNode< graph.length; childNode++) {
                // calculate the distance from startNode to childIdx
                //
                if((!visited[childNode])&&((graph[parentN][childNode]!=0)||(graph[childNode][parentN]!=0))) {
                    // there is an edge from parentN -> childNode
                    // update distance from startNode-> childNode
                    // it is distance from startNode -> parentN + edge between parentN and childNode
                    distances[childNode] = distances[parentN]+Math.max(graph[parentN][childNode],graph[childNode][parentN]);
                    // add child node
                    visited[childNode] = true;
                    toV.add(childNode);
                }
            }
        }
        for(int i=0;i<distances.length;i++) {
            if(i!= startNode) {
                if(distances[i]==0)
                    System.out.print("-1 ");
                else
                    System.out.print(distances[i]+" ");
            }
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println("Current working directory is "+System.getProperty("user.dir"));
       // Scanner scn = new Scanner(System.in);
        String fileN = "mindistances.txt";
        Scanner scn = Util.getInputFromFile(fileN);
        Util.outputtoFile(fileN);
        int queries = scn.nextInt();
        for(int i=0;i<queries; i++) {
            int nnodes = scn.nextInt();
            int nedges = scn.nextInt();
            // 0 mean -1
            // nodes go from 0 to nnodes-1
            int[][] graph = new int[nnodes][nnodes];
            for(int eCnt=0;eCnt< nedges; eCnt++) {
                graph[scn.nextInt()-1][scn.nextInt()-1]=6;
            }
            int startNode=scn.nextInt()-1;
            printDistances(graph,startNode);
            System.out.println();
        }
    }
}
