package datastruct;

import java.util.List;

/**
 * Created by ssiddiqu on 2/3/18.
 */
public class Tree<T> {
    T value;
    List<Tree> children;
    public Tree(T value) {
        this.value = value;
    }

}
