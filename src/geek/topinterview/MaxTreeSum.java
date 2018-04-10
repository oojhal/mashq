package geek.topinterview;

import datastruct.Tree;

import java.util.Comparator;

/**
 * Created by ssiddiqu on 4/5/18.
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.

 Example:

 Input: Root of below tree
 1
 / \
 2   3
 Output: 6
 */
public class MaxTreeSum {
    public static int maxTreeSum(Tree<Integer> tr) {
        int[] min = new int[]{Integer.MIN_VALUE};
        maxTreeSum(tr.getRoot(), min);
        return min[0];
    }

    /**
     * This function does two things:
     * returns max sum that starts from this node and goes to one of its child paths.
     * updates the currMax if the max sum passing through this node is more than the
     * currMax value passes
     * @param nd
     * @param currMax keeps track of the current max, value is updated
     * @param <T>
     * @return
     */
    public static int maxTreeSum(Tree.Node<Integer> nd, int[] currMax) {
        // keeps track of max sum starting at node and including one of child paths
        if(nd==null)
            return 0;
        int ndVal = nd.getValue();
        int maxOneSide = ndVal;
        // keeps track of max sum for a path that passes through the node. It can include
        // both child paths
        int maxAtCurr = ndVal;
        if ((nd.getChildren() != null) && (!nd.getChildren().isEmpty())) {
            // this keeps track of two child paths that do not include the current node
            int[] twoMaxs = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
            // at the end of the loop twoMaxs will contain the top two
            for (Tree.Node<Integer> child : nd.getChildren()) {
                // that returns the max path at current child
                int childMax = maxTreeSum(child, currMax);
                //childmaxs[0] is max path at node, childmax[1] is first max subtree and
                //childmax[1] is max second subtree
                int minofMaxs = twoMaxs[0] < twoMaxs[1] ? 0 : 1;
                if ((childMax> twoMaxs[minofMaxs])) {
                    // child Max is bigger than the smaller max which needs to be overwritten
                    twoMaxs[minofMaxs] = childMax;
                }
            }
            //twoMaxs will contain the two maximum child
            maxOneSide = max(ndVal, plus(ndVal,twoMaxs[0]), plus(ndVal,twoMaxs[1]));
            maxAtCurr = max(maxOneSide, plus(ndVal,twoMaxs[0],twoMaxs[1]));

        }
        if(maxAtCurr>currMax[0]) {
            currMax[0] = maxAtCurr;
        }
        return maxOneSide;
    }
    private static int plus(int...nums) {
        int sum=0;
        for(int num: nums) {
            if((num==Integer.MIN_VALUE)||(num==Integer.MAX_VALUE)) {
                return num;
            }
            sum +=num;
        }
        return sum;
    }
    private static int minus(int...nums) {
        int sum=0;
        for(int num: nums) {
            if((num==Integer.MIN_VALUE)||(num==Integer.MAX_VALUE)) {
                return num;
            }
            sum -=num;
        }
        return sum;
    }
    private static <T extends Comparable<T>> T maxOrmin(Comparator<T> cmp, T...vals) {
        T maxV = null;
        for(T val: vals) {
            if((maxV == null)||(cmp.compare(val, maxV)>0)) {
                maxV=val;
            }
        }
        return maxV;
    }
    private static <T extends Comparable<T>> T max(T...vals) {
        Comparator<T> cmp = Comparator.<T>naturalOrder();
        return maxOrmin(cmp, vals);
    }
    private static <T extends Comparable<T>> T min(T...vals) {
        Comparator<T> cmp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o2.compareTo(o1);
            }
        };
        return maxOrmin(cmp, vals);
    }
    public static void testMinMax() {
        int min = Integer.MIN_VALUE;
        System.out.println((min-1)+","+ Integer.MAX_VALUE);
        int max= Integer.MAX_VALUE;
        System.out.println((max+1)+","+ Integer.MIN_VALUE);
        System.out.print(minus(min,500));
        System.out.println(max(1,4,12,8));
        System.out.println(min(1,4,12,8));
        System.out.println(max("b","m","n","g"));
        System.out.println(min("b","m","n","g"));
    }
    public static void testMaxTreeSum() {
        Tree<Integer> tr = new Tree<>();
        Tree.Node<Integer> rootNd = new Tree.Node<>(10,0);
        tr.setRoot(rootNd);
        Object[][] ndata = {{0,1,2},{1,2,20},{1,3,1},{0,4,10},{4,5,-25},{5,6,3},{5,7,4} };
        tr.addNodes(ndata);
        System.out.println(tr);
        System.out.println(maxTreeSum(tr));
    }
    public static void main(String[] args) {
        testMinMax();
//        testMaxTreeSum();
    }
}
