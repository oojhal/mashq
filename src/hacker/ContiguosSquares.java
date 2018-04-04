/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

/**
 *
 * @author ssiddiqu
 */
public class ContiguosSquares {
    // up, down, left, right, up right, up left, down right, down left
    private static int[][] direct = {{-1,0},{1,0},{0,-1},{0,1},{-1,1},{-1,-1},{1,1},{1,-1}};
    // 0,1: 0,-1, 1,1, 1,-1, 1,0, -1,0, -1,1, -1,-1
    // 2: first one zero, second can be either 1 or -1
    // 3: first one 1, second one can be 1,0,-1
    // 3: first one -1, second one can be 1, 0, -1
    public static int largestRegion(int[][] sqrs) {
        boolean[][] visited = new boolean[sqrs.length][sqrs[0].length];
        int maxReg=0;
        for(int row=0; row< sqrs.length; row++) {
            for(int col=0; col<sqrs[row].length; col++) {
                if((!visited[row][col])&&(sqrs[row][col]!=0)) {
                    // visit the node
                    int newSz = visitDF(sqrs, row, col, visited);
                    if(newSz> maxReg) {
                        maxReg = newSz;
                    }

                }
            }
        }
        return maxReg;
    }
    // do DF search to visit all the connected cells from srqs[row][col]
    public static int visitDF(int[][] sqrs, int row, int col, boolean[][] visited) {
        int regCnt = 0;
        if(((row>=0)&&(row< sqrs.length) &&(col>=0) &&(col<sqrs[row].length)&&
            (!visited[row][col])&&(sqrs[row][col]!=0))) {
            visited[row][col]=true;
            regCnt++;
            for(int i=0; i< direct.length; i++) {
                regCnt += visitDF(sqrs, row+direct[i][0], col+direct[i][1], visited);
            }
        }
        return regCnt;
    }
    public static void testLargestRegion() {
        int[][] matrix = new int[][]{
            {0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}};
        System.out.println(largestRegion(matrix));
    }
    public static void main(String[] arg) {
        testLargestRegion();
    }
}
