package interviews;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ssiddiqu on 3/16/18.
 */
public class WordIn2DGrid {
    public static List<int[]> findWordCoordinates(String[] grid, String word) {
        List<int[]> ret= new LinkedList<>();
        for(int row=0; row< grid.length; row++) {
            for(int col=0; col< grid[row].length(); col++) {
                if((grid[row].charAt(col)==word.charAt(0))&&(findWord(grid,row,col, word))) {
                    ret.add(new int[] {row,col});
                }
            }
        }
        return ret;
    }
    public static boolean findWord(String[] grid, int x, int y, String word) {
        // search the word in all 8 directions
        boolean found = false;
        //for each direction
        for(int directI=0; directI< directions.length; directI++) {
            // go in the direction until either reached end of the word or
            // no more room in the 2d array
            int wordIndx = 1;
            int cx=x+directions[directI][0];
            int cy=y+ directions[directI][1];
            while((cx>=0)&&(cx<grid.length)&&(cy>=0)&&(cy<grid[cx].length())&&
                (wordIndx<word.length())&&(grid[cx].charAt(cy)==word.charAt(wordIndx))) {
                cx+= directions[directI][0];
                cy+= directions[directI][1];
                wordIndx++;
            }
            if(wordIndx == word.length()) {
                found=true;
                break;
            }
        }
        return found;

    }
    private static int[][] directions = {
        {1,0}, // down, only row increases
        {-1,0}, // up only row decreases
        {0,-1}, // left column decreases
        {0,1}, // right column increases
        {-1,-1}, // bottom left corner to upper right corner
        {1,1}, // upper left corner to bottom right corner
        {1,-1}, // upper right to lower left
        {-1,1} // lower left to upper right
    } ;
    public static void testFindWord() {
        String word="EEE";
        String[] grid={"GEEKSFORGEEKS",
            "GEEKSQUIZGEEK",
            "IDEQAPRACTICE"};
        List<int[]> coordinates = findWordCoordinates(grid,word);
        coordinates.forEach((arr) -> System.out.println(Arrays.toString(arr)));
    }
    public static void main(String[] args) {
        testFindWord();
    }
}
