package icf;

/**
 * Created by ssiddiqu on 3/17/18.
 */
public class EggDrop {
    /**
     * Have e eggs and f floors. Lower number floors are below higher number
     * floor e.g. floor 1 is below 2 which is below 3 and so on.
     * Eggs break only when dropped from some minimum height that corresponds to a floor
     * e.g. eggs may break when dropped from floor 3 or above but not when dropped from floor
     * 2 as floor 2 is lower than floor 3.
     * The relationship between e and f is not known i.e. e maybe lower than, equal to or higher
     * than f.
     * Find the minimum number of tries (not broken eggs)
     * that are guaranteed to discover the lowest floor from which eggs will break.
     * Note that the minimum number of tries is NOT the best case scenario. Rather it should cover the worst possible
     * outcomes. For example if there are 4 floors and 2 eggs, the egg is dropped from floor 4 and it does not break and then
     * it is dropped from floor 5 and it breaks, you found the answer in two tries. But that is not the minimum number of tries that are
     * guaranteed to provide the answer as it is possible that the egg does not break from 5th floor either.
     * Note that for finding guaranteed minimum number of tries the only thing that matters is the
     * number of eggs and the number of floors to check. It does not matter what
     * floor number is starting floor. For example the number of tries needed to find the floor
     * at which eggs breaks is the same for the following two situations.
     * (1) floors are from 1st floor to 3rd floor -> number of floors to check = 4
     * (2) floors are from 4th floor to 8th floor -> number of floors to check = 4
     * Following is the base table
     * Eggs         Floors          Tries
     * n                0               0
     * n                1               1
     * 1                n               n
     * Lets say now you need to find out the minimum number of tries to determine which floors the eggs break for
     * two eggs and two floors (again the floor numbers do no matter).
     * For finding the above answer you have unlimited number of eggs to discover the minimum number of tries for the given eggs, floor combination.
     * You can take one of the two possible routes. The most efficient route will provide the answer i.e. the minimum number of tries.
     * Here are the two routes we can take for two eggs and two floors. Whatever route yields the lower number of tries to find the answer is the route we
     * can take
     * Route 1. Drop the egg from higher floor. There are two possibilities.
     *  (a) Egg breaks. Left with 1 egg and 1 lower floor to check.
     *  (b) Egg does not break. Left with 2 eggs and 1 lower floor to check.
     * Since either of (a) or (b) possible, we need whatever case from (a) or (b) that covers the worse of the two possibilities i.e. the one that
     * requires higher number of tries as the higher number of tries will cover the other case as well.
     * Route 2. Drop the egg from lower floor. There are two possibilities.
     *  (a) Egg breaks. There is 1 egg no other higher floor to check
     *  (b) Egg does not break. Left with 2 eggs and 1 higher floor to check
     * Since either of (a) or (b) possible, we need whatever case from (a) or (b) that covers the worse of the two possibilities i.e. the one that
     * requires higher number of tries as the higher number of tries will cover the other case as well.
     * Now say T(e,f) represents minimum number of tries to find floor with e eggs and f floors.
     * T(2,2) (2 eggs and 2 floors) can be expressed as:
     * T(2,2) -> Minimum of [ Max of {( 1 egg and 1 flower), (2 eggs and 1 floor)} , Max of{ (1 egg and 0 floors), (2 eggs and 1 floor)}]
     * T(2,2) -> Min [ Max { T(1,1), T(2,1)}, Max {T(1,0), T(2,1)} ]
     * T(2,3) is 2 eggs and 3 floors. Drop from any floor and there are three floors. For each of those drops, there are two possible outcomes
     * (1) Drop from lowest floor. There are two possible outcomes
     *      (a) Egg breaks. Left with 1 egg no lower floors to check. Minimum tries T(1,0)
     *      (b) Egg does not break. Left with 2 eggs and 2 higher floors to check. Minimum tries T(2,2)
     * (2) Drop from middle floor. There are two possible outcomes
     *      (1) Egg breaks. Left with 1 egg and only 1 lower floor to check. Minimum T(1,1) tries needed.
     *      (2) Egg doesn't break. Left with 2 eggs and 1 higher floor to check. Minimum (2,1) tries needed.
     * (3) Drop from highest floor. There are two possibilities.
     *      (1) Egg breaks. Left with 1 egg and 2 lower floors to check. Minimum T(1,2) tries needed
     *      (2) Egg doesn't break. Left with 2 eggs and no floors to check. Minimum T(2,0) tries needed.
     *
     * T(2,3) = Minimum [ Max {T(1,0), T(2,2)}, Max{T(1,1), T(2,1)}, Max{T(1,2), T(2,0)}]
     * To to find the minimum number of tries from floor 'f' flags with 'e' eggs, 'f' possible routes are available i.e. to drop eggs from each
     * of the 'f' floors. Start from lowest i.e. first floor.
     * Scenario 1:
     * Let's say we are at floor currF (between 1- f). There are two possible scenarios:
     * (1) Egg breaks: Left with 1 less egg (e-1) and all the floor below the current floor to check. There are currF-1 floors below currF.
     *      T(e-1, currF-1)
     * (2) Egg doesn't break: Left with same number of eggs (e) and all the floors above current floor to check. There are f-currF such floor.
     *      T(e, f-currF)
     *  T(e, f) -> Min [Max {
     */
    public static int findMinTries(int e, int f) {
        int[][] tries = new int[e+1][f+1];
        for(int flr=0; flr<= f; flr++) {
            tries[0][flr]=0;
            tries[1][flr]=flr;
        }

        for(int numE=1; numE<=e; numE++) {
            for(int numF=0; numF<=f; numF++) {
                if(numF==0) {
                    tries[numE][numF]=0;
                }
                else {
                    int minTries=Integer.MAX_VALUE;
                    // for finding the minimum tries for floor numF with eggs numE
                    // try from all floors from 1 through numF. Drop the egg from each floor.
                    // If the egg broke left with numE-1 eggs and all the lower floors cflr-1
                    // If the egg did not break, left with numE eggs and all the upper floors to try
                    //
                    for(int cflr=1; cflr<= numF;cflr++) {
                        // For the minTries for floor clfr and eggs NumE
                        // try from floor clfr and egg broke. left with numE-1 eggs
                        // and all the lower floors (clfr-1) to try
                        // try from floor clfr and egg did not break. left with numE eggs and all the upper floors
                        // numF-clfr
                        int maxForCurrFlrs = 1+ Math.max(tries[numE-1][cflr-1], tries[numE][numF-cflr]);
                        if(maxForCurrFlrs<minTries) {
                            minTries=maxForCurrFlrs;
                        }
                    }
                    tries[numE][numF] = minTries;
                }

            }
        }
        return tries[e][f];
    }
    public static void testFindMinimumFloor() {
        int egg = 2, floor = 10;
        System.out.println("Minimum number of trials in worst case with "+egg+"  eggs and "+floor+
            " floors is "+findMinTries(egg, floor));
    }
    public static void main(String[] args) {
        testFindMinimumFloor();
    }
}
