package datastruct;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class MyQueue<T> {
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
        // must go to the end of the queue
            Node<T> lastNode = root;
            if(isEmpty())
                root = toInsert;
            else {
                while (lastNode.next != null) {
                    lastNode = lastNode.next;
                }
                lastNode.next = toInsert;
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
        MyQueue<String> q = new MyQueue<>();
        for(int val:vals) {
            q.push(String.valueOf(val));
        }
        System.out.println(q);
    }
}
