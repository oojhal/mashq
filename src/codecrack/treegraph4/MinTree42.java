package codecrack.treegraph4;

/**
 * Created by ssiddiqu on 2/10/18.
 */
public class MinTree42 {
    public int[] nodes;
    public MinTree42(int size) {
        nodes = new int[size];
    }
    public void constuctMinTree(int[] input) {
        if(nodes.length>input.length) {
            constructTree(input, 0, input.length - 1, nodes, 0);
        }
        else
            System.out.print("input bigger than tree size. Can't construct tree");
    }
    public String toString() {
        StringBuilder strb = new StringBuilder();
        int i=0;
        while(i<nodes.length) {
            strb.append("root="+String.valueOf(nodes[i++]));
            if(i>=nodes.length)
                break;
            strb.append("   Left Child="+String.valueOf(nodes[i++]));
            if(i>=nodes.length)
                break;
            strb.append("   Right Child="+String.valueOf(nodes[i++]));
        }
        return strb.toString();
    }

    /**
     *
     * @param input input array
     * @param istart start index
     * @param iend end index
     * @param tree tree to be filled
     * @param rootInd current root to be filled
     */
    public void constructTree(int[] input, int istart, int iend, int[] tree, int rootInd) {
        // only 1 element to be inserted
        if((istart>iend)||(istart<0)||(iend>=input.length)||(rootInd<0)||(rootInd>=tree.length)) {
            return;
        }
        if(istart==iend) {
            // only 1 element left in the tree
            tree[rootInd] = input[istart];
        }
        else {
            // find the middle if the input
            int imiddleInd = ((istart+iend+1)/2);
            tree[rootInd] = input[imiddleInd];
            // left subtree
            constructTree(input, istart, imiddleInd-1, tree, 2*(rootInd+1)-1);
            constructTree(input, imiddleInd+1, iend, tree, 2*(rootInd+1));
            // right subtree


        }
    }
    public static void main(String[] args) {
        int[] vals = {1,2,3,4,5,6,7,8,9,10};
        MinTree42 mint = new MinTree42(vals.length*2);
        mint.constuctMinTree(vals);
        System.out.print("VisitedTree: "+mint);
    }
}
