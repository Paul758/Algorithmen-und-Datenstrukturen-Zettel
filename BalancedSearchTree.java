import java.util.ArrayList;

public class BalancedSearchTree<T extends Comparable<T>> {
    Node<T> root;
    float alpha;

    public BalancedSearchTree(float alpha){
        this.alpha = alpha;
        this.root = null;
    }

    public BalancedSearchTree(){
        this.root = null;
    }

    public void insertion(Node<T> z){
        treeInsert(z);
        float treeBalance = calculateRootBalance(root);
        if(treeBalance < alpha || treeBalance > 1 - alpha){
            rebalance(z);
        }
    }

    private void treeInsert(Node<T> z) {
        Node<T> y = root;
        while(y != null){
            z.parent = y;
            if(z.key.compareTo(y.key) < 0){
                y = y.left;
            } else {
                y = y.right;
            }
        }
        if(z.parent == null){
            root = z;
        } else {
            if(z.key.compareTo(z.parent.key) < 0){
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
    }
    public void deletion(Node<T> z){
        treeDelete(z);
        float treeBalance = calculateRootBalance(root);
        if(treeBalance < alpha || treeBalance > 1 - alpha){
            rebalance(z);
        }
    }
    private void treeDelete(Node<T> z) {
        Node<T> y;
        Node<T> x;

        if(z.left == null || z.right == null){
            y = z;
        } else {
            y = treeSuccessor(z);
        }

        if(y.left != null){
            x = y.left;
        } else {
            x = y.right;
        }
        if(x != null){
            x.parent = y.parent;
        }
        if(y.parent == null){
            root = x;
        } else if (y.equals(y.parent.left)){
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        if(!(y.equals(z))){
            z.key = y.key;
        }

    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if(y.right != null){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        } else if(x.equals(x.parent.right)){
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        } else if(x.equals(x.parent.left)){
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void doubleLeftRotate(Node<T> x ){
        Node<T> y = x.right;
        Node<T> z = y.left;
        if(z.left != null){
            z.left.parent = x;
            x.right = z.left;
        }
        if(z.right != null){
            z.right.parent = y;
            y.left = z.right;
        }
        if(x.parent == null){
            root = z;
        } else if(x.equals(x.parent.left)){
            x.parent.left = z;
        } else {
            x.parent.right = z;
        }

        x.parent = z;
        z.left = x;
        y.parent = z;
        z.right = y;
    }

    public void doubleRightRotate(Node<T> x ){
        Node<T> y = x.left;
        Node<T> z = y.right;
        if(z.right != null){
            z.right.parent = x;
            x.left = z.right;
        }
        if(z.left != null){
            z.left.parent = y;
            y.right = z.left;
        }
        if(x.parent == null){
            root = z;
        } else if(x.equals(x.parent.right)){
            x.parent.right = z;
        } else {
            x.parent.left = z;
        }

        x.parent = z;
        z.right = x;
        y.parent = z;
        z.left = y;
    }

    private void rebalance(Node<T> x){
        while(x.parent != null){
            if(calculateRootBalance(x) < alpha){
                if(calculateRootBalance(x.right) <= (1 / (2 - alpha))){
                    leftRotate(x);
                } else {
                    doubleLeftRotate(x);
                }
            }
            if(calculateRootBalance(x) > 1 - alpha) {
                if(calculateRootBalance(x.left) >= (1 - (1 / (1 - alpha)))){
                    rightRotate(x);
                } else {
                    doubleRightRotate(x);
                }
            }
            x = x.parent;
        }
    }

    public float calculateRootBalance(Node<T> node) {
        //countNodesMax nodes in T
        float countNodesMax = 0;
        countNodesMax = countNodes(node, countNodesMax);
        //countNodesMax nodes in subtree
        float countNodesSubTree = 0;
        countNodesSubTree = countNodes(node.left, countNodesSubTree);

        return (countNodesSubTree + 1) / (countNodesMax + 1);
    }

    public float countNodes(Node<T> x, float count){
        if(x != null){
            count = count + 1;
            count = countNodes(x.left,count);
            count = countNodes(x.right, count);

        }
        return count;
    }

    public T search(Node<T> x, T k){
        if(x == null || x.key.compareTo(k) == 0){
            return k;
        }
        if(k.compareTo(x.key) < 0){
            return search(x.left, k);
        } else {
            return search(x.right, k);
        }
    }

    public Node<T> min(Node<T> x){
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    public Node<T> max(Node<T> x){
        while(x.right != null){
           x = x.right;
        }
        return x;
    }

    public Node<T> treeSuccessor(Node<T> x){
        Node<T> y = x.parent;
        if(x.right != null){
            return min(x.right);
        }
        while(y != null && x.equals(y.right)){
            x = y;
            y = x.parent;
        }
        return y;
    }

    public ArrayList<T> toSortedArrayList(Node<T> x){
        return inorderTreeToList(new ArrayList<>(),root);
    }

    private ArrayList<T> inorderTreeToList(ArrayList<T> sortedList, Node<T> x) {
        if(x != null){
            inorderTreeToList(sortedList,x.left);
            sortedList.add(x.key);
            inorderTreeToList(sortedList, x.right);
        }
        return sortedList;
    }

}
