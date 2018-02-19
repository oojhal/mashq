package datastruct;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class Stack <T> {
    private static class Node<T> {
        public Node(T val) {
            this.value= val;
            this.next= null;
        }
        private T value;
        // front of the queue
        private Node<T> next;
    }
    private Node<T> root;
    public void push(T val) {
        Node<T> toInsert = new Node(val);
        // must go to the front of the stack
        if(isEmpty())
            root = toInsert;
        else {
            toInsert.next = root;
            root = toInsert;
        }
    }
    public T pop() {
        if(isEmpty()) {
            return null;
        }
        T val = root.value;
        // fine to have null root
        root = root.next;
        return val;
    }
    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return root.value;
    }
    public boolean isEmpty() {
        return (root == null);
    }
    public String toString() {
        StringBuilder strb = new StringBuilder();
        if(!isEmpty()) {
            Node<T> curr = root;
            while(curr!=null) {
                if(curr!=root) {
                    strb.append(",");
                }
                strb.append(curr.value);
                curr = curr.next;
            }
        }
        return strb.toString();
    }
    public static void main(String[] args) {
        int[] vals = {0,1,2,3,4,5,6};
        Stack<String> stk = new Stack<>();
        for(int val:vals) {
            stk.push(String.valueOf(val));
        }
        System.out.println(stk);
    }
}
