package geek.topinterview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ssiddiqu on 4/12/18.
 */
public class MovieLengths {
    public static List<Set<Integer>> movieLengths(int[] mLengths, int duration, int numMovies) {

        // need to keep track of movies that have already been used
        // for each call, this will be different
        Set<Integer> used = new HashSet<>();
        List<Set<Integer>> mList = new ArrayList<>();
        movieLengths(mLengths, duration, numMovies,used, mList);
        return mList;
    }
    private static void movieLengths(int[] mLengths, int durationLeft, int numLeft, Set<Integer> used, List<Set<Integer>> mList) {
        // if there are no movies left and no duration left then used list contains movie legths that sum
        // to duration
        if((numLeft == 0)&&(durationLeft==0)) {
            if(!mList.contains(used)) {
                mList.add(used);
            }
        }
        // if there are some movies left
        else if (numLeft > 0) {
            for (int i = 0; i < mLengths.length; i++) {
                // if the current movie is not in the used list
                if (!used.contains(mLengths[i])) {
                    Set<Integer> nUsed = new HashSet<>(used);
                    // for each sub call need to supply a new list
                    nUsed.add(mLengths[i]);
                    movieLengths(mLengths, (durationLeft - mLengths[i]), numLeft - 1, nUsed, mList);
                }
            }
        }
    }
    public static void testMovieLengths() {
        int[] mLengths = new int[] {3,10,5,12,4,9,1};
        int numMovies = 3;
        int fDuration = 18;
        System.out.println(movieLengths(mLengths,fDuration,numMovies));
    }
    public static void main(String[] args) {
        testMovieLengths();
    }
}
